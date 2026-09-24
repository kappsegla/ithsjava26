package com.example.java26.exercises.week5;

public abstract class Vehicle {
    private final String manufacturer;

    public Vehicle(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void goTo(String dest){
        IO.println("Färdas till " + dest);
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void describeVehicle(){
        IO.println(" - Manufacturer: " + getManufacturer());
        IO.println(" - Type: " + this.getClass().getSimpleName());  //Reflection
        if( this instanceof NoiseMaker noiseMaker){
            noiseMaker.makeNoise();
        }
        if( this instanceof MotorVehicle motorVehicle){
            motorVehicle.refuel();
        }
    }

    static void main() {
        var bicycle = new Bicycle("Skeppshult");
        bicycle.goTo("Stockholm");
        IO.println(bicycle.getManufacturer());
        var car = new Car("Volvo", MotorVehicle.FuelType.ELECTRIC);
        car.goTo("Göteborg");
        IO.println(car.getManufacturer());
        var motorboat = new Motorboat("Speedboats Inc.", MotorVehicle.FuelType.PETROL);
        motorboat.goTo("Oslo");
        motorboat.describeVehicle();
        IO.println(motorboat.getManufacturer());
        var sailboat = new Sailboat("X-Yachts");
        sailboat.goTo("Germany");
        IO.println(sailboat.getManufacturer());
    }
}
