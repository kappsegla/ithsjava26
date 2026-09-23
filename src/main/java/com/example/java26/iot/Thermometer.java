package com.example.java26.iot;

public class Thermometer extends AbstractDevice {
    @Override
    public String getStatus() {
        return "Thermometer is " + (on ? "ON" : "OFF") +
                ", Temperature: " + (on ? Math.random() * 60 - 25 : "Unknown");
    }
}
