package com.example.java26.cli;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringParserTest {
    //"2,2" -> 4
    //"" -> 0
    //"3" -> 3
    //"1,2,3" -> 6
    //null -> 0
    //"1;2;3" -> 6
    StringParser sp = new StringParser();

    @Test
    void emptyStringReturns0() {
        int result = sp.parseAndAdd("");
        assertEquals(0, result);
    }

    @Test
    void oneNumberShouldReturnThatNumber() {
        int result = sp.parseAndAdd("1");
        assertEquals(1, result);
    }

    @Test
    void anotherNumberShouldReturnThatNumber() {
        int result = sp.parseAndAdd("2");
        assertEquals(2, result);
    }

    @Test
    void nullShouldReturn0() {
        int result = sp.parseAndAdd(null);
        assertEquals(0, result);
    }

    @Test
    void splitOnCommaAndSumValues() {
        int result = sp.parseAndAdd("1,2");
        assertEquals(3, result);
    }

    @Test
    void splitMultipleValuesOnCommaAndSum() {
        int result = sp.parseAndAdd("1,2,3");
        assertEquals(6, result);
    }
    
    @Test
    void splittingUsingSemicolon() {
        int result = sp.parseAndAdd("2;3;4");
        assertEquals(9, result);
    }
    


}
