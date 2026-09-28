package com.example.java26.cli;

public class Safe {
    private int value;

    public Safe(int initialValue) {
        this.value = initialValue;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if( value < 0)
            throw new IllegalArgumentException();
        this.value = value;
    }
}
