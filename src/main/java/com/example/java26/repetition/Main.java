package com.example.java26.repetition;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Person person = new Person("Kalle", 22);
        try {
            var lowerCaseName = person.name().toLowerCase();
            System.out.println(lowerCaseName);
        } catch (RuntimeException e) {  //Unchecked exception
            System.out.println("Error happened");
        }

        try {
            List<String> lines = Files.readAllLines(Path.of("test.txt"));
        } catch (IOException e) {
            System.out.println("Error reading from file " + e.getMessage());
        }

        Map<String, Person> persons = new HashMap<>();
        persons.put("Kalle", person);
        var p = persons.get("Kalle");

        Storage<Person> storage = new Storage<>(p);
        Storage<String> stringStorage = new Storage<>("Hello World");
    }
}

class Storage<T> {
    private final T value;

    public Storage(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}

record Person(String name, int age) {
}
