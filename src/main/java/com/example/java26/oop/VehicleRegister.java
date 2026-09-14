package com.example.java26.oop;

public class VehicleRegister {
    static void main() {
        Vehicle vehicle = new Vehicle();
        Vehicle vehicle2 = new Vehicle("Volvo V70", 4, 60, 35);

        IO.println(vehicle.getModel());
        IO.println(vehicle2.getModel());

    }
}
