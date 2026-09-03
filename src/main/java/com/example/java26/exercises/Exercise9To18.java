package com.example.java26.exercises;

public class Exercise9To18 {

    static void main() {
        IO.println(sum(10, 10));
        int sum = sum(10, 10);
        double celcius = 20.0;
        double fahrenheit = 0.0;
        IO.println("Temperature is " + celcius + " ℃ and " + String.format("%.1f",toFahrenheit(celcius)) + " ℉");
        IO.println("Temperature is " + fahrenheit + " ℉ and " + String.format("%.1f",toCelcius(fahrenheit)) +" ℃");

        fizzBuzz(5);
        IO.println(fizzBuzz2(3));

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
    //Comparing primitive types can be done using ==
    public static boolean compare(int a, int b) {
        return a == b;
    }
    //Comparing object types must use equals
    public static boolean compare(String a, String b) {
        return a.equals(b);
    }

    public static boolean divisibleByFive(int number){
        return number % 5 == 0;
    }

    public static void fizzBuzz(int number) {
        if(number % 3 == 0 && number % 5 == 0)
            IO.println("FizzBuzz");
        else if( number % 3 == 0)
            IO.println("Fizz");
        else if( number % 5 == 0)
            IO.println("Buzz");
        else
            IO.println(number);
    }

    public static String fizzBuzz2(int number) {
        if(number % 3 == 0 && number % 5 == 0)
            return "FizzBuzz";
        else if( number % 3 == 0)
            return "Fizz";
        else if( number % 5 == 0)
            return "Buzz";
        else
            return "" + number;
    }
}
