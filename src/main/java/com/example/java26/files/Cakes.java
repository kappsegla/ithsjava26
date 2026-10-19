package com.example.java26.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Cakes {
    static void main() {
        String homeFolder = System.getProperty("user.home");
        Path homePath = Path.of(homeFolder);
        Path path = homePath.resolve("java26", "cakes.csv");

        try {
            Files.lines(path).skip(1)
                    .map(line -> line.trim().split(","))
                    .map(cake -> new Cake(
                            Integer.parseInt(cake[0]),
                            cake[1].trim(),
                            Integer.parseInt(cake[2])))
                    .forEach(IO::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

record Cake(int id, String name, int price) {
}
