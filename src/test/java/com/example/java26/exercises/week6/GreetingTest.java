package com.example.java26.exercises.week6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GreetingTest {
    Greeting greeting = new Greeting();

    @Test
    void nameIsNull() {
                var result = greeting.greet(null);
        assertEquals("Hello, my friend.", result);
    }

    @Test
    void allUpperCaseName() {
        var result = greeting.greet("KALLE");
        assertEquals("HELLO KALLE!", result);
    }

    @Test
    void normalName() {
        var result = greeting.greet("Kalle");
        assertEquals("Hello, Kalle.", result);
    }

    @Test
    void emptyName() {
        var result = greeting.greet("");
        //Should we handle this same as null?
        assertEquals("HELLO !", result);
    }
}
