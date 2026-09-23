package com.example.java26.machine;

public class SodaMaker implements BeverageMachine {

    @Override
    public void makeBeverage() {
        IO.println("🌀 Carbonating the beverage");
        IO.println("🥤 Filling cup");
    }
}
