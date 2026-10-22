package com.example.java26.ssltest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    static void main() {

        //To use ca certs from operating system in windows
        //Add VM option -Djavax.net.ssl.trustStoreType=WINDOWS-ROOT
        //Should be fine if updating to Oracle JDK 27.0.1.0.2

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
