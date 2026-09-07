package com.example.java26;

public class MethodOverloading {
    static void main() {
        int i = 1;
        sayHi(i);
        String name = "Martin";
        sayHi(name);
    }

    static void sayHi() {
        System.out.println("Hi!");
    }
    static void sayHi(String namn) {
        System.out.println("Hi, " + namn);
    }
    static void sayHi(int value) {
        System.out.println("Hi! " + value);
    }

}
