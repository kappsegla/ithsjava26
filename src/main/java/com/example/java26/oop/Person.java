package com.example.java26.oop;

import java.util.Objects;

/**
 * En oföränderlig (immutable) klass som representerar en person.
 * Klassen är final och alla fält är private final för att förhindra förändring av tillstånd.
 */
public final class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Namn får inte vara tomt eller null");
        }
        if (age < 0) {
            throw new IllegalArgumentException("Ålder kan inte vara negativ");
        }
        this.name = name;
        this.age = age;
    }

    public Person(String name) {
        this(name, 0);
    }

    // Copy-konstruktor
    public Person(Person other) {
        Objects.requireNonNull(other, "Person får inte vara null");
        this.name = other.name;
        this.age = other.age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    /**
     * Returnerar en ny Person-instans med uppdaterat namn.
     */
    public Person withName(String newName) {
        return new Person(newName, this.age);
    }

    /**
     * Returnerar en ny Person-instans med uppdaterad ålder.
     */
    public Person withAge(int newAge) {
        return new Person(this.name, newAge);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
