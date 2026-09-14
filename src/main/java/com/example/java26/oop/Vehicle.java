package com.example.java26.oop;

public class Vehicle {
    private String model;
    private int passengers;   //Number of passengers
    private double fuelCap;
    private double mpg;

    public Vehicle() {
        model = "Unknown";  //Alt: Use an empty String with ""
        passengers = 1;
        fuelCap = 1;
        mpg = 1;
    }

    public Vehicle(String model, int passengers, double fuelCap, double mpg) {
        if (passengers < 0)
            throw new IllegalArgumentException("Passengers cannot be negative");
        this.model = model;
        this.passengers = passengers;
        this.fuelCap = fuelCap;
        this.mpg = mpg;
    }

    public String getModel() {
        return model;
    }

    public void setPassengers(int passengers) {
        if (passengers < 0)
            throw new IllegalArgumentException("Passengers cannot be negative");
        this.passengers = passengers;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setFuelCap(double fuelCap) {
        this.fuelCap = fuelCap;
    }

    public double getFuelCap() {
        return fuelCap;
    }

    public void setMpg(double mpg) {
        this.mpg = mpg;
    }

    public double getMpg() {
        return mpg;
    }

    public double computeRange() {
        return mpg * fuelCap;
    }
}
