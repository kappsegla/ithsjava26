package com.example.java26.generics;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CarRegister {

    private final List<Car> cars = new ArrayList<>();

    static void main() {
        var register = new CarRegister();
        register.cars.add(new Car("ABC123", Color.RED));
        register.cars.add(new Car("DUP345", Color.BLACK));
        register.cars.add(new Car("ADF934", Color.BLACK));
        register.cars.add(new Car("OYP983", Color.BLACK));
        register.cars.add(new Car("KTR562", Color.BLUE));
        register.cars.add(new Car("MNB781", Color.WHITE));
        register.cars.add(new Car("PLX220", Color.GRAY));
        register.cars.add(new Car("ZQD449", new Color(0x22, 0x88, 0x22)));   // green-ish
        register.cars.add(new Car("HJS904", Color.YELLOW));
        register.cars.add(new Car("WCP667", new Color(255, 140, 0)));        // orange
        register.cars.add(new Car("BRF128", new Color(128, 0, 128)));        // purple
        register.cars.add(new Car("WXM552", Color.GRAY));
        register.cars.add(null);

        IO.println("Does car ABC123 exist? " + register.cars.contains(new Car("ABC123", Color.RED)));

        Car toSearchFor = new Car("ABC123", Color.RED);
        IO.println(register.contains(toSearchFor));


    }

    private boolean contains(Car toSearchFor) {
        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            if (toSearchFor.equals(car)) {
                return true;
            }
        }
        return false;
    }
}

record Car(String licensePlate, Color color) {
}
