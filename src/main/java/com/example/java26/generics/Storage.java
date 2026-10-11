package com.example.java26.generics;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Storage<T> {
    private T value;

    public Storage(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    record Pair<T1, T2>(T1 first, T2 second) {
    }

    static void main() {
        Storage<String> storage = new Storage<>("Hello");
        storage.setValue("World");
        String value = storage.getValue();
        IO.println(value);

        ArrayList<String> strings = new ArrayList<>();
        strings.add("Hello");
        strings.add("World");
        ArrayList<Integer> integers = new ArrayList<>();
        var pair = new Pair<>("Hello", LocalDateTime.now());
    }
}
