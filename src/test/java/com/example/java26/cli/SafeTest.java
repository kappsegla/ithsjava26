package com.example.java26.cli;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SafeTest {

    @Test
    @DisplayName("Creating a new Safe with initial value")
    void constructorWithInitialValueg() {
        int initialValue = 10;

        Safe safe = new Safe(initialValue);

        var result = safe.getValue();
        assertEquals(10, result);
    }

    @Test
    @DisplayName("Setting a new value")
    void setValueToOneHundred() {
        int initialValue = 10;
        Safe safe = new Safe(initialValue);

        safe.setValue(100);

        var result = safe.getValue();
        assertEquals(100, result);
    }

    @Test
    @DisplayName("Less than zero throws IllegalArgumentException")
    void setValueToLessThanZero() {
        int initialValue = 10;
        Safe safe = new Safe(initialValue);

        assertThrows(IllegalArgumentException.class,
                () -> safe.setValue(-1)
        );
    }
}
