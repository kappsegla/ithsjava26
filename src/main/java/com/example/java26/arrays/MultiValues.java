package com.example.java26.arrays;

import java.util.Scanner;

public class MultiValues {
    static void main() {
        int[] numbers = new int[10];
        int count = 0;

        while (count < numbers.length) { //Stoppa när arrayen är full
            //Fråga användaren efter ett tal
            int number;
            try {
                number = Integer.parseInt(IO.readln("Enter a number:"));
            } catch (NumberFormatException e) {
                IO.println("Please enter a number!");
                continue;
            }
            if (number == 0)
                break;
            //Spara i nästa lediga plats i en array
            numbers[count++] = number;
        }

        for (int i = 0; i < count; i++) {
            IO.print(numbers[i] + ",");
        }
    }
}
