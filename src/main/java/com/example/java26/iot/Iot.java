package com.example.java26.iot;

public class Iot {

    static void main() {
        Device device1 = new Thermometer();

        IO.println(device1.getStatus());
        device1.turnOn();
        IO.println(device1.getStatus());
    }
}
