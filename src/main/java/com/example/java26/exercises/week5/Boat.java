package com.example.java26.exercises.week5;

public class Boat extends Vehicle {

    public Boat(String manufacturer) {
        super(manufacturer);
    }

    @Override
    public void goTo(String dest) {
        IO.println("Färdas med båt till " + dest);
    }
}
