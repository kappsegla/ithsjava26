package com.example.java26.functional;

import java.time.LocalTime;

public class Pure {

    private static int count = 0;

    //Pure function
    public static int add(int a, int b) {
        return a + b;
    }

    public static String greeting(String name) {
        return "Hello " + name;
    }

    public static void greeting() {
        String name = IO.readln("Vad heter du? "); //Effect Input
        String message = greeting(name); //Pure function
        IO.println(message); //Effect Output
    }

    public static int counter() {
        return count++;
    }

    public static int number() {
        count++;
        return 2;
    }

    public static String sayHello(String name) {
        if (LocalTime.now().isAfter(LocalTime.of(12, 0, 0))) {
            return "Good afternoon";
        }
        return "Hello " + name;
    }

    public static String sayHello(String name, LocalTime time) {
        if (time.isAfter(LocalTime.of(12, 0, 0))) {
            return "Good afternoon";
        }
        return "Hello " + name;
    }

    static void main() {
        LocalTime time = LocalTime.now();
        sayHello("Kalle", time);
        sayHello("Kalle", time);
    }
}
