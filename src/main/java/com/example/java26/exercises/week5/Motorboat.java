package com.example.java26.exercises.week5;

public class Motorboat extends Boat implements NoiseMaker {
    public Motorboat(String manufacturer) {
        super(manufacturer);
    }

    @Override
    public void goTo(String dest) {
        IO.println("Färdas med motorbåt till " + dest);
    }

    @Override
    public void makeNoise() {
        IO.println("vroooOOOM!");
    }
}
