package com.example.java26.agent;

import com.fasterxml.jackson.annotation.JsonInclude;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AIAgent {
    private static final String HOST = "https://openrouter.ai";
    private static final String MODEL = "google/gemini-3.8-flash";
    //private static final String MODEL = "deepseek/deepseek-v4.1-flash";

    public static void main(String[] args) throws IOException, InterruptedException {
        final String API_KEY = System.getenv("OPENROUTER_API_KEY");

        HttpClient client = HttpClient.newHttpClient();
        ObjectMapper mapper = new ObjectMapper();

        String instructions = "Svara kort. Max 2 meningar.";
        Reasoning reasoning = new Reasoning("medium");
        List<InputItem> history = new ArrayList<>();

        //Create tool list (flat format for Responses API)
        List<Tool> tools = List.of(
                new Tool("function", "get_current_datetime",
                        "Returns the current date and time in UTC",
                        Map.of("type", "object",
                                "properties", Map.of(),
                                "required", List.of()
                        )),
                new Tool("function", "view_file",
                        "Read file contents",
                        Map.of("type", "object",
                                "properties", Map.of("path",
                                        Map.of("type", "string",
                                                "description", "The file path to read")),
                                "required", List.of("path")
                        )),
                new Tool("function", "create_file",
                        "Create a new file",
                        Map.of("type", "object",
                                "properties", Map.of("path",
                                        Map.of("type", "string",
                                                "description", "The file path to create"),
                                        "content",
                                        Map.of("type", "string",
                                                "description", "Content of the file")),
                                "required", List.of("path", "content")
                        )),
                new Tool("function", "list_directory",
                        "List files and directories in a directory",
                        Map.of("type", "object",
                                "properties", Map.of("path",
                                        Map.of("type", "string",
                                                "description", "The directory path to list")),
                                "required", List.of("path")
                        ))
        );

        System.out.println("Chatten är igång! Skriv 'quit' för att avsluta.\n");
        while (true) {
            String input = IO.readln("You: ");
            if ("quit".equals(input))
                break;

            history.add(InputItem.message("user", input));

            try {
                while (true) {
                    var requestBody = mapper.writeValueAsString(new Request(MODEL, instructions, history, tools, reasoning));
                    // Skapa HTTP-förfrågan
                    HttpRequest request = HttpRequest.newBuilder()
                            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                            .header("Content-Type", "application/json")
                            .header("Authorization", "Bearer " + API_KEY)
                            .uri(URI.create(HOST + "/api/v1/responses"))
                            .build();

                    // Skicka och läs svar med felhantering och retry
                    Response or = sendWithRetry(client, request, mapper);

                    // Kolla output array för function_call items eller message items
                    boolean hasFunctionCall = false;

                    for (OutputItem item : or.output()) {
                        if ("function_call".equals(item.type())) {
                            hasFunctionCall = true;
                            IO.println("AI:n vill använda ett verktyg: " + item.name() + " (" + item.id() + ")");

                            // Lägg till function_call item i historiken
                            history.add(InputItem.functionCall(
                                    item.id(), item.call_id(), item.name(), item.arguments()));

                            // Kör verktyget och lägg till resultat
                            String toolOutput = runTool(item.name(), item.arguments(), mapper);
                            history.add(InputItem.functionCallOutput(item.call_id(), toolOutput));
                        }
                    }

                    if (hasFunctionCall) {
                        continue;
                    }

                    // Hitta message item och extrahera text
                    for (OutputItem item : or.output()) {
                        if ("message".equals(item.type()) && item.content() != null) {
                            StringBuilder reply = new StringBuilder();
                            for (OutputContentPart part : item.content()) {
                                if ("output_text".equals(part.type())) {
                                    reply.append(part.text());
                                }
                            }
                            if (reply.length() > 0) {
                                IO.println("Assistant: " + reply);
                                history.add(InputItem.message("assistant", reply.toString()));
                                break;
                            }
                        }
                    }
                    break;
                }
            } catch (ApiException e) {
                IO.println("[Fel] " + e.code() + ": " + e.getMessage() + " (HTTP " + e.statusCode() + ")");
                if (e.isRetryable()) {
                    IO.println("Försök igen med en ny fråga.");
                } else {
                    IO.println("Kontrollera API-nyckeln eller kontot och försök igen.");
                }
                // Ta bort det misslyckade meddelandet från historiken
                if (!history.isEmpty()) {
                    history.remove(history.size() - 1);
                }
            }
        }
    }

    private static String runTool(String name, String argumentsJson, ObjectMapper mapper)
            throws IOException {
        Map<String, Object> arguments = mapper.readValue(argumentsJson, Map.class);
        switch (name) {
            case "get_current_datetime": {
                DateTimeResult result = getCurrentDateTime();
                return mapper.writeValueAsString(result);
            }
            case "view_file": {
                String pathStr = arguments.get("path").toString();
                Path path = Path.of(pathStr);
                if (confirm("Tillåt att " + path + " läses."))
                    return mapper.writeValueAsString(Files.readString(path));
                else
                    return mapper.writeValueAsString("User denied access to the file.");
            }
            case "create_file": {
                String pathStr = arguments.get("path").toString();
                Path path = Path.of(pathStr);
                if (confirm("Tillåt att skapa/skriva över " + path)) {
                    Files.createDirectories(path.getParent());
                    Files.writeString(path, arguments.get("content").toString());
                    return mapper.writeValueAsString("File created");
                } else
                    return mapper.writeValueAsString("User denied creating the file.");
            }
            case "list_directory": {
                String pathStr = arguments.getOrDefault("path", ".").toString();
                Path path = Path.of(pathStr);
                if (confirm("Tillåt att lista katalogen " + path)) {
                    try (var stream = Files.list(path)) {
                        String result = stream
                                .map(p -> p.getFileName().toString() + (Files.isDirectory(p) ? "/" : ""))
                                .collect(Collectors.joining("\n"));
                        return mapper.writeValueAsString(result);
                    }
                } else {
                    return mapper.writeValueAsString("User denied listing the directory.");
                }
            }
            default:
                IO.println("Unknown tool requested: " + name);
                return mapper.writeValueAsString("Unknown tool: " + name);
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

    private static Response sendWithRetry(HttpClient client, HttpRequest request, ObjectMapper mapper)
            throws IOException, InterruptedException {
        int maxRetries = 3;
        Duration baseDelay = Duration.ofSeconds(2);

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int status = response.statusCode();

            // HTTP 200 – försök pars som normal response
            if (status == 200) {
                try {
                    return mapper.readValue(response.body(), Response.class);
                } catch (Exception e) {
                    // Response var inte en giltig Response – kolla om det är ett fel
                    ApiError error = mapper.readValue(response.body(), ApiErrorResponse.class).error();
                    throw new ApiException(error.code(), error.message(), status);
                }
            }

            // HTTP-fel – pars strukturerat fel
            ApiError error;
            try {
                error = mapper.readValue(response.body(), ApiErrorResponse.class).error();
            } catch (Exception e) {
                throw new ApiException("unknown", response.body(), status);
            }

            // 429 Rate limit – retry med exponential backoff
            if (status == 429 || "rate_limit_exceeded".equals(error.code())) {
                if (attempt < maxRetries) {
                    long retryAfter = parseRetryAfter(response);
                    long delayMs = retryAfter > 0 ? retryAfter * 1000 : baseDelay.toMillis() * (1L << (attempt - 1));
                    IO.println("[Varning] Rate limit nådd. Väntar " + (delayMs / 1000) + "s innan ny försök...");
                    Thread.sleep(delayMs);
                    continue;
                }
            }

            // 5xx server-fel – retry med exponential backoff
            if (status >= 500) {
                if (attempt < maxRetries) {
                    long delayMs = baseDelay.toMillis() * (1L << (attempt - 1));
                    IO.println("[Varning] Serverfel (" + status + "). Väntar " + (delayMs / 1000) + "s innan ny försök...");
                    Thread.sleep(delayMs);
                    continue;
                }
            }

            // 401, 402, 403, 408, 400 – icke-återhämtbara fel, bryt direkt
            throw new ApiException(error.code(), error.message(), status);
        }

        throw new ApiException("max_retries", "Maximalt antal försök uppnåddes", 429);
    }

    private static long parseRetryAfter(HttpResponse<String> response) {
        try {
            String retryAfter = response.headers().map().getOrDefault("Retry-After", List.of()).stream().findFirst().orElse("");
            return retryAfter.isEmpty() ? 0 : Long.parseLong(retryAfter);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}

record DateTimeResult(String now, String timezone) {
}

record Tool(String type, String name, String description, Map<String, Object> parameters) {
}

// --- Request data models for /api/v1/responses ---

@JsonInclude(JsonInclude.Include.NON_NULL)
record Request(String model, String instructions, List<InputItem> input, List<Tool> tools, Reasoning reasoning) {
}

@JsonInclude(JsonInclude.Include.NON_NULL)
record Reasoning(String effort) {
}

@JsonInclude(JsonInclude.Include.NON_NULL)
record InputItem(String type, String role, List<ContentPart> content,
                 String id, String call_id, String name, String arguments,
                 String output) {
    // Message item: type="message", role, content
    public static InputItem message(String role, String text) {
        return new InputItem("message", role, List.of(new ContentPart("input_text", text)),
                             null, null, null, null, null);
    }

    // Function call item: type="function_call", id, call_id, name, arguments
    public static InputItem functionCall(String id, String callId, String name, String arguments) {
        return new InputItem("function_call", null, null,
                             id, callId, name, arguments, null);
    }

    // Function call output item: type="function_call_output", call_id, output
    public static InputItem functionCallOutput(String callId, String output) {
        return new InputItem("function_call_output", null, null,
                             null, callId, null, null, output);
    }
}

record ContentPart(String type, String text) {
}

// --- Response data models for /api/v1/responses ---

record Response(String id, List<OutputItem> output, Usage usage) {
}

@JsonInclude(JsonInclude.Include.NON_NULL)
record OutputItem(String type, String id, String role, String status,
                  List<OutputContentPart> content,
                  String call_id, String name, String arguments) {
}

record OutputContentPart(String type, String text) {
}

record Usage(int input_tokens, int output_tokens, int total_tokens) {
}

// --- Error handling data models ---

record ApiErrorResponse(ApiError error, Object metadata) {
}

record ApiError(String code, String message) {
}

class ApiException extends RuntimeException {
    private final String code;
    private final int statusCode;

    public ApiException(String code, String message, int statusCode) {
        super(message);
        this.code = code;
        this.statusCode = statusCode;
    }

    public String code() {
        return code;
    }

    public int statusCode() {
        return statusCode;
    }

    public boolean isRetryable() {
        return statusCode == 429 || statusCode >= 500;
    }
}
