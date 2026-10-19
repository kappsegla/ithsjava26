package com.example.java26.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ReadFromFile {
    static void main() {
        String homeFolder = System.getProperty("user.home");
        Path homePath = Path.of(homeFolder);
        Path path = homePath.resolve("java26", "file.txt");

        try {
            List<String> lines = Files.readAllLines(path);
            lines.forEach(IO::println);
        } catch (IOException e) {
            IO.println("Error while reading file " + e.getMessage());
        }
    }
}
