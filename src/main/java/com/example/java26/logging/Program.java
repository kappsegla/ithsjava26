package com.example.java26.logging;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Program {
    public static void main(String[] args) {

        configureLogging();

        Logger logger = Logger.getLogger(Program.class.getName());

        logger.info("Applikationen startar");

        CustomerService customerService = new CustomerService();
        customerService.createCustomer("Anna");
        customerService.createCustomer("");

        DatabaseService databaseService = new DatabaseService();
        databaseService.loadCustomers();

        logger.info("Applikationen avslutas");
    }

    private static void configureLogging() {
        try {

            Files.createDirectories(Path.of("logs"));

            Logger rootLogger = Logger.getLogger("");

            // Ta bort standard ConsoleHandler
            rootLogger.setUseParentHandlers(false);

            FileHandler fileHandler =
                    new FileHandler("logs/application.log", true);

            fileHandler.setFormatter(new SimpleFormatter());

            rootLogger.addHandler(fileHandler);

        } catch (IOException e) {
            throw new RuntimeException("Kunde inte konfigurera loggning", e);
        }
    }
}
