package com.example.java26.exercises;

public class Exercise9To18 {

    static void main() {
        IO.println(sum(10, 10));
        int sum = sum(10, 10);
        double celcius = 20.0;
        double fahrenheit = 20.0;
        IO.println("Temperature is " + celcius + " ℃ and " + toFahrenheit(celcius) + " ℉");
        IO.println("Temperature is " + fahrenheit + " ℉ and " + toCelcius(fahrenheit) +" ℃");
    }

    private static double toCelcius(double fahrenheit) {
        return (fahrenheit - 32) / 1.8;
    }

    public static double toFahrenheit(double temp) {
        return temp * 1.8 + 32;
    }

    public static int sum(int a, int b) {
        return a + b;
    }


}
