package com.example.java26.repetition;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

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

        List<String> strings = new ArrayList<>();
        List<String> immutableStrings = List.of("Hello", "World");
        //immutableStrings.add("Hello");  //Throws UnsupportedException
        immutableStrings.stream()
                .filter(s -> !s.equals("Hello"))
                .forEach(IO::println);

        IO.println(maybeAValue().orElse("Standard value for empty optional"));
        IO.println(maybeAValue().orElse("Standard value for empty optional"));
        IO.println(maybeAValue().orElse("Standard value for empty optional"));
        IO.println(maybeAValue().orElse("Standard value for empty optional"));
    }

    public static Optional<String> maybeAValue() {
        if (Math.random() < 0.5) {
            return Optional.empty();
        }
        return Optional.of("A value");
    }

    public static List<String> maybeValues() {
        if (Math.random() < 0.5) {
            return List.of();
        }
        return List.of("A value", "Another value");
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
