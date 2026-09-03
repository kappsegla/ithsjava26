package com.example.java26.exercises;

public class Exercise3 {
    static void main() {
        int age = 17;

        if (age >= 18)
            IO.println("Du är gammal nog att ta körkort");
        else {
            int yearsToDrivingLicence = 18 - age;
            IO.println("Du behöver vänta " + yearsToDrivingLicence + " år.");
        }
    }
}
