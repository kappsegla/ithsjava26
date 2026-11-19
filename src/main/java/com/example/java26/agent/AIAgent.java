package com.example.java26.agent;

import com.fasterxml.jackson.annotation.JsonInclude;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AIAgent {
    static final String HOST = "https://openrouter.ai";
    static final String MODEL = "google/gemini-3.8-flash";
    static final ObjectMapper MAPPER = JsonMapper.builder().build();
    static final HttpClient CLIENT = HttpClient.newHttpClient();

    static final String INSTRUCTIONS = "Svara kort. Max 2 meningar.";

    public static void main(String[] args) throws Exception {
        var history = new ArrayList<InputItem>();
        IO.println("Chatten är igång! Skriv 'quit' för att avsluta.");
        while (true) {
            String in = IO.readln("You: ");
            if ("quit".equals(in)) break;
            history.add(InputItem.message("user", in));
            int mark = history.size();                     // för att kunna rulla tillbaka vid fel
            history.add(InputItem.message("user", in));
            try {
                for (int step = 0; step < 10; step++) {      // tak på antal verktygsvarv
                    Response r = send(new Request(MODEL, INSTRUCTIONS, history, TOOLS));
                    boolean called = false;
                    for (OutputItem item : r.output()) {
                        if (!"function_call".equals(item.type())) continue;
                        called = true;
                        IO.println("[tool] " + item.name() + " " + item.arguments());
                        history.add(InputItem.functionCall(item.id(), item.call_id(), item.name(), item.arguments()));
                        history.add(InputItem.functionCallOutput(item.call_id(),
                                runTool(item.name(), item.arguments())));
                    }
                    if (called) continue;                      // modellen får se resultatet
                    String reply = text(r);
                    IO.println("Assistant: " + reply);
                    history.add(InputItem.message("assistant", reply));
                    break;
                }
            } catch (Exception e) {
                IO.println("[Fel] " + e.getMessage());
                history.subList(mark, history.size()).clear();   // rulla tillbaka hela turen, inte bara sista meddelandet
            }
        }
    }

    static Response send(Request req) throws Exception {
        var http = HttpRequest.newBuilder(URI.create(HOST + "/api/v1/responses"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + System.getenv("OPENROUTER_API_KEY"))
                .POST(HttpRequest.BodyPublishers.ofString(MAPPER.writeValueAsString(req)))
                .build();
        for (int attempt = 1; ; attempt++) {
            var res = CLIENT.send(http, HttpResponse.BodyHandlers.ofString());
            int s = res.statusCode();
            if (s == 200) return MAPPER.readValue(res.body(), Response.class);
            if ((s == 429 || s >= 500) && attempt < 3) {
                Thread.sleep(2000L << (attempt - 1));          // 2 s, 4 s, ...
                continue;
            }
            throw new RuntimeException("HTTP " + s + ": " + res.body());
        }
    }

    // svaret är en lista output-items. textsvaret ligger i items av typen "message"
    static String text(Response r) {
        var sb = new StringBuilder();
        for (OutputItem item : r.output())
            if ("message".equals(item.type()) && item.content() != null)
                for (OutputContentPart p : item.content())
                    if ("output_text".equals(p.type())) sb.append(p.text());
        return sb.toString();
    }

    static String json(Object o) {
        try {
            return MAPPER.writeValueAsString(o);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static final List<Tool> TOOLS = new ArrayList<>(List.of(
            new Tool("function", "get_current_datetime", "Returns the current date and time in UTC",
                    Map.of("type", "object", "properties", Map.of(), "required", List.of()))));

    // kör ett verktyg. fel går tillbaka till modellen som text i stället för att krascha loopen
    static String runTool(String name, String argumentsJson) {
        try {
            return json(execute(name, argumentsJson));
        } catch (Exception e) {
            return json("Error: " + e);
        }
    }

    @SuppressWarnings("unchecked")
    static String execute(String name, String argumentsJson) throws Exception {
        String raw = argumentsJson == null || argumentsJson.isBlank() ? "{}" : argumentsJson;
        Map<String, Object> arguments = MAPPER.readValue(raw, Map.class);
        switch (name) {
            case "get_current_datetime":
                return java.time.ZonedDateTime.now(java.time.ZoneOffset.UTC).toString();
            default:
                return "Unknown tool: " + name;
        }
    }

}

// ersätter Request, lägger till InputItem och ContentPart
@JsonInclude(JsonInclude.Include.NON_NULL)
record Request(String model, String instructions, List<InputItem> input, List<Tool> tools) {
}

@JsonInclude(JsonInclude.Include.NON_NULL)
record InputItem(String type, String role, List<ContentPart> content,
                 String id, String call_id, String name, String arguments, String output) {
    // fälten från id och framåt används först i steg 5
    static InputItem message(String role, String text) {
        var t = "assistant".equals(role) ? "output_text" : "input_text";
        return new InputItem("message", role, List.of(new ContentPart(t, text)),
                null, null, null, null, null);
    }

    static InputItem functionCall(String id, String callId, String name, String args) {
        return new InputItem("function_call", null, null, id, callId, name, args, null);
    }

    static InputItem functionCallOutput(String callId, String output) {
        return new InputItem("function_call_output", null, null, null, callId, null, null, output);
    }
}

record Tool(String type, String name, String description, Map<String, Object> parameters) {
}

record ContentPart(String type, String text) {
}

record Response(String id, List<OutputItem> output) {
}

record OutputItem(String type, String id, List<OutputContentPart> content,
                  String call_id, String name, String arguments) {
}

record OutputContentPart(String type, String text) {
}
