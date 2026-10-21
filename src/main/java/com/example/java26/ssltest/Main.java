package com.example.java26.ssltest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    static void main() {
        try (HttpClient httpClient = HttpClient.newHttpClient()){
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://dv-g3b-emudhra.we-have-a-problem.com"))
                    .GET()
                    .build();

            var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
