package com.example.java26.cli;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GreeterTest {

    @Test
    @DisplayName("Greet with good morning before noon")
    void greetWithGoodMorningBeforeNoon() {
        Greeter greeter = new Greeter(Clock.fixed(Instant.parse("2026-09-10T11:00:00.00Z"), ZoneId.of("Z")));

        var greeting = greeter.greet("Martin");

        assertEquals("Good morning Martin", greeting);
    }


}
