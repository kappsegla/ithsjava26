package com.example.java26.exercises.week5;

public class Car extends Vehicle {

    public Car(String manufacturer) {
        super(manufacturer);
    }

    @Override
    public void goTo(String dest) {
        IO.println("Färdas med bil till " + dest);
    }
}
