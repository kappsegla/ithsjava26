package com.example.java26.exercises.week5;

public class Bicycle extends Vehicle {

    public Bicycle(String manufacturer) {
        super(manufacturer);
    }

    @Override
    public void goTo(String dest) {
       IO.println("Färdas med cykel till " + dest);
    }
}
