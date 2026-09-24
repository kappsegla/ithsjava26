package com.example.java26.exercises.week5;

public abstract class MotorVehicle extends Vehicle {
    private final FuelType fuelType;

    public MotorVehicle(String manufacturer, FuelType fuelType) {
        super(manufacturer);
        this.fuelType = fuelType;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public abstract void refuel();

    public enum FuelType {
        PETROL,
        DIESEL,
        ELECTRIC
    }
}
