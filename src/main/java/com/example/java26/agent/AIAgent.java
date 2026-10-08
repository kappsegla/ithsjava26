package com.example.java26.agent;

import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


public class AIAgent {

    private static final String HOST = "https://openrouter.ai";
    private static final String MODEL = "google/gemini-3.8-flash";

    static void main() throws IOException, InterruptedException {
        final String API_KEY = System.getenv("OPENROUTER_API_KEY");

        HttpClient client = HttpClient.newHttpClient();
        ObjectMapper mapper = new ObjectMapper();
        // Skapa en meddelandelista med en fråga.
        var messages = List.of(new Message("user", "Svara kort: Vad är Java?"));
        var requestBody = mapper.writeValueAsString( new Request(MODEL, messages, null));

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
    }
}

record Request(String model, List<Message> messages, Object tools) {}
record Message(String role, String content) {}
record OpenRouterResponse(List<Choice> choices) {}
record Choice(int index, MessageResponse message) {}
record MessageResponse(String role, String content) {}
