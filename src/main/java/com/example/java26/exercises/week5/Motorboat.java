package com.example.java26.exercises.week5;

public class Motorboat extends MotorVehicle implements NoiseMaker {
    public Motorboat(String manufacturer, FuelType fuelType) {
        super(manufacturer, fuelType);
    }

    @Override
    public void goTo(String dest) {
        IO.println("Färdas med motorbåt till " + dest);
    }

    @Override
    public void makeNoise() {
        IO.println("vroooOOOM!");
    }

    @Override
    public void refuel() {
        String message = switch (getFuelType()) {
            case ELECTRIC -> "Laddar båten. 🪫";
            case DIESEL -> "Tankar båten med diesel";
            case PETROL -> "Fyller båten med brandfarlig vätska 💥";
        };
        IO.println(message);
    }
}
