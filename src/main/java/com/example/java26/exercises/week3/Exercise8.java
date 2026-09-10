package com.example.java26.exercises.week3;

public class Exercise8 {
    static void main() {
        var person = new Person("John", 25, "Göteborg");
        IO.println(person);
        IO.println("Person with name: " + person.name());
        IO.println(new Person("Jehova", 30, "Stockholm"));
        IO.println(new Person("Jeff", 45, "Malmö"));
    }
}

record Person(String name, int age, String stad) {
}
