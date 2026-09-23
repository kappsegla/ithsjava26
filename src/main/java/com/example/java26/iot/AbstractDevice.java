package com.example.java26.iot;

public abstract class AbstractDevice implements Device{
    protected boolean on;

    @Override
    public void turnOn() {
        this.on = true;
    }

    @Override
    public void turnOff() {
        this.on = false;
    }
}
