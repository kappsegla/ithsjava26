package com.example.java26.generics;

import java.util.ArrayList;
import java.util.List;

public class ListDemo {

    static void main() {
        List<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");

        printAll(strings);
    }

    //Using wildcard to accept any type
    public static void printAllItems(Iterable<?> values){
        for (Object value : values) {
            System.out.println(value);
        }
    }

    //Generic method using Type Inference
    public static <T> void printAll(Iterable<T> values) {
        for (T s : values) {
            System.out.println(s);
        }
    }
}
