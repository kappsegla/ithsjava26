package com.example.java26.exercises.week7;

import java.util.HashMap;
import java.util.Map;

public class Exercise5 {
    static Map<String, String> huvudstad = new HashMap<>();

    static {
        huvudstad.put("Sweden", "Stockholm");
        huvudstad.put("India", "New Delhi");
        huvudstad.put("USA", "Washington D.C.");
        huvudstad.put("Spain", "Madrid");
        huvudstad.put("Finland", "Helsinki");
    }

    static void main(String[] args) {
        IO.println(getCapital("Sweden"));
        IO.println(getCapital("India"));
        IO.println(getCapital("USA"));
        IO.println(getCapital("Spain"));
        IO.println(getCapital("Finland"));
        System.exit(0);
    }

    public static String getCapital(String land) {
        return huvudstad.get(land);
    }

}
