package com.example.java26.cli;

public class StringParser {
    public int parseAndAdd(String s) {
        if( s.isEmpty() ) return 0;
        return Integer.parseInt(s);
    }
}
