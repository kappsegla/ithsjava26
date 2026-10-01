package com.example.java26.exercises.week6;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void isAdult() {
        Person person = new Person(18);
        assertTrue(person.isAdult());  //Standard Junit assert
        assertThat(person.isAdult()).isTrue();  //AssertJ
    }

    @Test
    void isNotAdult() {
        Person person = new Person(17);
        assertFalse(person.isAdult());
    }
}
