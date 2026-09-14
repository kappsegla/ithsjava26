package com.example.java26.oop;

import java.util.Vector;

public class VehicleRegister {
    static void main() {
        Vehicle vehicle = new Vehicle();
        Vehicle vehicle2 = new Vehicle("Volvo V70", 4, 60, 35);

//        IO.println(vehicle.getModel());
//        IO.println(vehicle.getPassengers());
//
//        IO.println(vehicle2.getModel());
//        IO.println(vehicle2.getPassengers());

        Vehicle vehicle3 = vehicle2;
        vehicle3.setPassengers(10);

        IO.println(vehicle2.getPassengers());
        IO.println(vehicle3.getPassengers());

        increaseNumberOfPassengers(vehicle3);
        IO.println(vehicle3.getPassengers());
    }

    public static void increaseNumberOfPassengers(Vehicle vehicle) {
        vehicle.setPassengers(vehicle.getPassengers() + 1);
    }
}
