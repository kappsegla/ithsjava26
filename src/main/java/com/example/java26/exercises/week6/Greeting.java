package com.example.java26.exercises.week6;

public class Greeting {
    public String greet(String name) {
        if (name == null)
            return "Hello, my friend.";

        if(isAllUpperCase(name))
            return "HELLO " + name +"!";

        return "Hello, " + name + ".";
    }

    private boolean isAllUpperCase(String name) {
        char[] charArray = name.toCharArray();
        for (char c : charArray) {
            if (!Character.isUpperCase(c))
                return false;
        }
        return true;
    }
}
