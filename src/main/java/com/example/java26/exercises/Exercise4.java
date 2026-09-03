package com.example.java26.exercises;

public class Exercise4 {
    static void main() {
        IO.println("Enter two numbers:");
        double tal1 = Double.parseDouble(IO.readln());
        double tal2 = Double.parseDouble(IO.readln());
        double average = (tal1 + tal2) / 2.0;
        IO.println("The average is " + average);

        //Alt1
        double max = Math.max(tal1, tal2);

        //Alt2
        if(tal1 > tal2){
            IO.println("The largest number is " + tal1);
        }
        else if(tal2 > tal1){
            IO.println("The largest number is " + tal2);
        }
        else
            IO.println("Talen är lika");
    }
}
