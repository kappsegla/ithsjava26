package com.example.java26.exercises.week7;

import java.util.HashSet;
import java.util.Set;

public class Exercise4 {


    public static Set<String> findUniqueWords(String text) {
        var set =  new HashSet<String>(Exercise2.toList(text.split("[ \n.]")));
        set.removeAll(Set.of(""));
        return set;
    }
}
