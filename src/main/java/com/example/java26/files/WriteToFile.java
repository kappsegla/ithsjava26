package com.example.java26.files;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WriteToFile {
    static void main() throws IOException {
        String homeFolder = System.getProperty("user.home");
        Path homePath = Path.of(homeFolder);
        Path savePath = homePath.resolve("java26");
        IO.println(savePath);
        Files.createDirectories(savePath);
        IO.println(Files.exists(savePath));
        IO.println("Is directory: " + Files.isDirectory(savePath));
        IO.println("Is file: " + Files.isRegularFile(savePath));
        Path filePath = savePath.resolve("file.txt");
        IO.println(Files.exists(filePath));
        IO.println("Is directory: " + Files.isDirectory(filePath));
        IO.println("Is file: " + Files.isRegularFile(filePath));
        try {
            Files.createFile(filePath);
        } catch (FileAlreadyExistsException e) {
            IO.println("File " + filePath + " already exists");
        }
        Files.writeString(filePath, "I'm fine åäö ûü", StandardCharsets.ISO_8859_1);
    }
}
