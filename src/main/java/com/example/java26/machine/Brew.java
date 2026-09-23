package com.example.java26.machine;

public class Brew {
    static void main() {
        BeverageMachine machine1 = new CoffeeMaker();
        BeverageMachine machine2 = new TeaMaker();
        BeverageMachine machine3 = new SodaMaker();

        machine1.makeBeverage();
        machine2.makeBeverage();
        machine3.makeBeverage();
    }
}
