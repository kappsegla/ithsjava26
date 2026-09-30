package com.example.java26.cli;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringParserTest {
    //"2,2" -> 4
    //"" -> 0
    //"3" -> 3
    //"1,2,3" -> 6
    //null -> 0


    @Test
    void emptyStringReturns0() {
        StringParser sp = new StringParser();
        int result = sp.parseAndAdd("");
        assertEquals(0, result);
    }







}
