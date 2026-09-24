package com.example.java26.exercises.week5;

public class Car extends Vehicle implements NoiseMaker {

    public Car(String manufacturer) {
        super(manufacturer);
    }

    @Override
    public void goTo(String dest) {
        IO.println("Färdas med bil till " + dest);
    }

    @Override
    public void makeNoise() {
        IO.println("Vrrrm!");
    }
}
