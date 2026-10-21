package com.example.java26.logging;

import java.util.logging.Logger;

public class CustomerService {

    private static final Logger LOG =
            Logger.getLogger(CustomerService.class.getName());

    public void createCustomer(String name) {

        LOG.info("Försöker skapa kund");

        if (name == null || name.isBlank()) {
            LOG.warning("Kundnamn saknas");
            return;
        }

        LOG.info("Kund skapad: " + name);
    }
}
