package com.example.java26.machine;

public class CoffeeMaker extends AbstractBeverageMaker implements BeverageMachine {
    @Override
    protected void brew() {
        IO.println("🫘 CoffeeMaker brew");
    }
}
