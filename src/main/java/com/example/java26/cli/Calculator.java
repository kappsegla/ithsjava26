package com.example.java26.cli;

import java.text.BreakIterator;

public class Calculator {

    public static int add(int a, int b) {
        return a + b;
    }

    public static int graphemeCount(String input){
        BreakIterator breakIterator = BreakIterator.getCharacterInstance();
        breakIterator.setText(input);
        int count = 0;
        while( breakIterator.next() != BreakIterator.DONE ){
            count++;
        }
        return count;
    }
}
