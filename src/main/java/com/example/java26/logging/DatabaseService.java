package com.example.java26.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseService {

    private static final Logger LOG =
            Logger.getLogger(DatabaseService.class.getName());

    public void loadCustomers() {

        LOG.info("Läser kunder från databasen");

        try {
            simulateDatabaseError();
        } catch (Exception e) {
            LOG.log(Level.SEVERE,
                    "Databasfel uppstod",
                    e);
        }
    }

    private void simulateDatabaseError() {
        throw new RuntimeException("Connection timeout");
    }
}
