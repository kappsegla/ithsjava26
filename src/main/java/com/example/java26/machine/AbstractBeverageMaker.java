package com.example.java26.machine;

public abstract class AbstractBeverageMaker implements BeverageMachine {
    @Override
    public final void makeBeverage() {
        boilWater();
        brew();
        pourInCup();
    }

    protected abstract void brew();

    private void boilWater() {
        IO.println("♨️ Boiling Water");
    }
    private void pourInCup() {
        IO.println("☕ Pouring in Cup");
    }
}
