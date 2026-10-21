package com.example.java26.logging;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Demo {


    static void main(String[] args) throws IOException {
        Level level = Level.SEVERE;
        for (var arg : args) {
            if (arg.toLowerCase().startsWith("--loglevel=")) {
                level = Level.parse(arg.substring("--loglevel=".length()));
            }
        }

        Logger logger = Logger.getLogger(Demo.class.getName());
        logger.setLevel(level);
        FileHandler fileHandler = new FileHandler("application.log", true);
        fileHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(fileHandler);
        logger.setUseParentHandlers(false);

        IO.println("Starting application...");

        var user = new User("Martin", "safkjl32", 1);

        logger.info(() -> "User created: " + user.name() + "," + user.userID());

        try {
            logger.info("Trying to read config");
            Files.readString(Path.of("config"));
            logger.info("Trying to read config");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading config", e);
            return;
        }

        IO.println("Stopping application...");
    }
}

record User(String name, String password, int userID) {
}
