package com.example.java26.exercises.week5;

public class Sailboat extends Boat{
    public Sailboat(String manufacturer) {
        super(manufacturer);
    }

    @Override
    public void goTo(String dest) {
        IO.println("Färdas med segelbåt till " + dest);
    }
}
