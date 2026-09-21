package com.example.java26.oop2;

import java.util.Objects;

public class Person {
    public static final int MIN_AGE = 0;
    private final String name;
    private final int age;

    public Person(String name, int age) {
        if( age < MIN_AGE) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Person person)) return false;

        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(name);
        result = 31 * result + age;
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
