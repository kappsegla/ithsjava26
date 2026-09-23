package com.example.java26.iot;

public class Light extends AbstractDevice {
    @Override
    public String getStatus() {
        return on ? "ON" : "OFF";
    }
}
