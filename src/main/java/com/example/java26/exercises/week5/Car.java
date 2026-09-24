package com.example.java26.exercises.week5;

public class Car extends MotorVehicle implements NoiseMaker {

    public Car(String manufacturer, FuelType fuelType) {
        super(manufacturer, fuelType);
    }

    @Override
    public void goTo(String dest) {
        IO.println("Färdas med bil till " + dest);
    }

    @Override
    public void makeNoise() {
        IO.println("Vrrrm!");
    }

    @Override
    public void refuel() {
       String message =  switch (getFuelType()) {
            case ELECTRIC -> "Laddar bilen";
            case DIESEL -> "Tankar traktorn";
            case PETROL -> "Tankar bilen med dinosaurie juice";
        };
       IO.println(message);
    }
}
