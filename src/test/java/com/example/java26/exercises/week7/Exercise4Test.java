package com.example.java26.exercises.week7;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Exercise4Test {


    @Test
    void test() {
        String text = """
                Once upon a time there where Java.
                Java where the best.
                """;
        Set<String> words = Exercise4.findUniqueWords(text);

        assertEquals(words.size(), 9);
        assertTrue(words.contains("Java"));
//        assertEquals(Set.of("Once","upon","a","time","there","where", "Java",
//                "the", "best"),words);
        assertThat(words).containsOnly("Once","upon","a","time","there","where", "Java",
                "the", "best");

    }
}
