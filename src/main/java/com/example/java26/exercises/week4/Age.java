package com.example.java26.exercises.week4;

public class Age {
    private int years;

    public Age(int years) {
        if (years < 0 || years > 150)
            throw new IllegalArgumentException("The age cannot be negative or above 150");
        this.years = years;
    }

    static void main() {
        Age age = new Age(70);        //Change value to test boundaries
        IO.println(age.years);

        try {
            Age person2 = new Age(-5); //Ogiltigt
            IO.println("Person2: " + person2.years);
        } catch (IllegalArgumentException e) {
            IO.println("Person 2 kunde inte skapas: " + e.getMessage());
        }
    }
}
