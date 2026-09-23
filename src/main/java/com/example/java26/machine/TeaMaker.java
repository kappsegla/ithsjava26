package com.example.java26.machine;

public class TeaMaker extends AbstractBeverageMaker {
    @Override
    protected void brew() {
        IO.println("🍵 TeaMaker brew");
    }
}
