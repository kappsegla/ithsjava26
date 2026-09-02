package com.example.java26;

public class Methods {
    static void main() {
        final boolean DEBUG = false;
        printGreeting();

        int tal1 = readIntegerFromKeyboard();
        if (DEBUG)
            IO.println("Log: tal1=" + tal1);
        int tal2 = readIntegerFromKeyboard();
        if (DEBUG)
            IO.println("Log: tal2=" + tal2);
        int sum = add(tal1, tal2);
        IO.println("Summan blir: " + sum);

        printShutDownMessage();
    }

    static void printGreeting() {
        IO.println("Starting application....");
        IO.println("Welcome to Methods 1.0");
        IO.println("======================");
    }

    static void printShutDownMessage() {
        IO.println("Shutting down application");
        IO.println("Thanks for your support!");
    }

    static int readIntegerFromKeyboard() {
        int value = Integer.parseInt(IO.readln("Enter an integer: "));
        return value;
    }

    static int add(int a, int b) {
        return a + b;
    }

}
