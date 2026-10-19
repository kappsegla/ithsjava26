package com.example.java26.agent;

import com.fasterxml.jackson.annotation.JsonInclude;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class AIAgent {

    private static final String HOST = "https://openrouter.ai";
    private static final String MODEL = "google/gemini-3.8-flash";

    public static void main(String[] args) throws IOException, InterruptedException {
        final String API_KEY = System.getenv("OPENROUTER_API_KEY");

        HttpClient client = HttpClient.newHttpClient();
        ObjectMapper mapper = new ObjectMapper();

        List<Message> history = new ArrayList<>();

        history.add(new Message("system", "Svara kort. Max 2 meningar."));
        //Create tool list
        List<Tool> tools = List.of(
                new Tool("function",
                        new ToolFunctionSpec("get_current_datetime",
                                "Returns the current date and time in UTC",
                                Map.of("type", "object",
                                        "properties", Map.of(),
                                        "required", List.of()
                                )
                        )),
                new Tool("function",
                        new ToolFunctionSpec("view_file",
                                "Read file contents",
                                Map.of("type", "object",
                                        "properties", Map.of("path",
                                                Map.of("type", "string",
                                                        "description", "The file path to read")),
                                        "required", List.of("path")
                                )
                        )),
                new Tool("function",
                        new ToolFunctionSpec("create_file",
                                "Create a new file",
                                Map.of("type", "object",
                                        "properties", Map.of("path",
                                                Map.of("type", "string",
                                                        "description", "The file path to create"),
                                                "content",
                                                Map.of("type", "string",
                                                        "description", "Content of the file")),
                                        "required", List.of("path", "content")
                                )
                        ))
        );

        System.out.println("Chatten är igång! Skriv 'quit' för att avsluta.\n");
        while (true) {
            String input = IO.readln("You: ");
            if ("quit".equals(input))
                break;

            history.add(new Message("user", input));

            while (true) {

                var requestBody = mapper.writeValueAsString(new Request(MODEL, history, tools));
                // Skapa HTTP-förfrågan
                HttpRequest request = HttpRequest.newBuilder()
                        .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Bearer " + API_KEY)
                        .uri(URI.create(HOST + "/api/v1/chat/completions"))
                        .build();

                // Skicka och läs svar
                var response = client.send(request, HttpResponse.BodyHandlers.ofString());
                OpenRouterResponse or = mapper.readValue(response.body(), OpenRouterResponse.class);

                //Känn av om modellen vill köra ett verktyg
                var choice = or.choices().get(0);
                MessageResponse msg = choice.message();
                String finish = choice.finish_reason();

                if ("tool_calls".equals(finish)) {
                    IO.println("AI:n vill använda ett verktyg: " + msg.tool_calls());

                    history.add(new Message("assistant", msg.content(), null, msg.tool_calls()));

                    for (ToolCall toolCall : msg.tool_calls()) {
                        if ("get_current_datetime".equals(toolCall.function().name())) {
                            DateTimeResult result = getCurrentDateTime();
                            String toolOutput = mapper.writeValueAsString(result);
                            history.add(new Message("tool", toolOutput, toolCall.id()));
                        } else if ("view_file".equals(toolCall.function().name())) {
                            Map<String, Object> arguments = mapper.readValue(toolCall.function().arguments(), Map.class);
                            String pathStr = arguments.get("path").toString();
                            Path path = Path.of(pathStr);
                            String result = "";
                            if (confirm("Tillåt att " + path + " läses."))
                                result = Files.readString(path);
                            else
                                result = "User denied access to the file.";
                            String toolOutput = mapper.writeValueAsString(result);
                            history.add(new Message("tool", toolOutput, toolCall.id()));
                        } else if ("create_file".equals(toolCall.function().name())) {
                            Map<String, Object> arguments = mapper.readValue(toolCall.function().arguments(), Map.class);
                            String pathStr = arguments.get("path").toString();
                            Path path = Path.of(pathStr);
                            String result = "";
                            if (confirm("Tillåt att skapa/skriva över " + path)) {
                                Files.createDirectories(path.getParent());
                                Files.writeString(path, arguments.get("content").toString());
                                result = "File created";
                            } else
                                result = "User denied creating the file.";
                            String toolOutput = mapper.writeValueAsString(result);
                            history.add(new Message("tool", toolOutput, toolCall.id()));
                        } else
                            IO.println("Unknown tool requested: " + toolCall.function().name());
                    }
                } else if ("stop".equals(finish)) {
                    String reply = msg.content();
                    IO.println("Assistant: " + reply);
                    history.add(new Message("assistant", reply));
                    break;
                } else {
                    throw new IllegalStateException("Unexpected value: " + finish);

                }
            }
        }
    }

    private static boolean confirm(String question) {
        String answer = IO.readln(question + " (j/n): ")
                .trim()
                .toLowerCase();
        return answer.equals("j") || answer.equals("ja");
    }

    private static DateTimeResult getCurrentDateTime() {
        var now = ZonedDateTime.now(ZoneOffset.UTC);
        return new DateTimeResult(now.toString(), "UTC");
    }
}

record DateTimeResult(String now, String timezone) {
}

record Request(String model, List<Message> messages, List<Tool> tools) {
}

record Tool(String type, ToolFunctionSpec function) {
}

record ToolFunctionSpec(String name, String description, Map<String, Object> parameters) {
}

@JsonInclude(JsonInclude.Include.NON_NULL)
record Message(String role, String content, String tool_call_id, List<ToolCall> tool_calls) {
    public Message(String role, String content, String tool_call_id) {
        this(role, content, tool_call_id, null);
    }

    public Message(String role, String content) {
        this(role, content, null, null);
    }
}

record OpenRouterResponse(List<Choice> choices) {
}

record Choice(int index, MessageResponse message, String finish_reason) {
}

record MessageResponse(String role, String content, List<ToolCall> tool_calls) {
}

record ToolCall(String id, String type, ToolFunction function) {
}

record ToolFunction(String name, String arguments) {
}
