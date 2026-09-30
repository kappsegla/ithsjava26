package com.example.java26.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultipleIntegersTest {

    MultipleIntegers newInstance = new MultipleIntegers();

    @Test
    void newInstanceShouldBeEmpty() {
        assertEquals(0, newInstance.size());
    }



}
