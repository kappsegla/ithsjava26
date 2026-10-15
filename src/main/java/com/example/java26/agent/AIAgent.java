package com.example.java26.agent;

import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class AIAgent {

    private static final String HOST = "https://openrouter.ai";
    private static final String MODEL = "google/gemini-3.8-flash";

    static void main() throws IOException, InterruptedException {
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
                        ))
        );

        System.out.println("Chatten är igång! Skriv 'quit' för att avsluta.\n");
        while (true) {
            String input = IO.readln("You: ");
            if ("quit".equals(input))
                break;

            history.add(new Message("user", input));

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

            //Skriv ut svaret från modellen
            String reply = or.choices().getFirst().message().content();
            IO.println("Assistant: " + reply);

            history.add(new Message("assistant", reply));
        }
    }
}

record Request(String model, List<Message> messages, List<Tool> tools) {
}

record Tool(String type, ToolFunctionSpec function) {
}

record ToolFunctionSpec(String name, String description, Map<String, Object> parameters) {
}

record Message(String role, String content) {
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
