package com.example.java26.exercises;

public class Exercise5 {

    static void main() {
        double tal1 = Double.parseDouble(IO.readln());
        double tal2 = Double.parseDouble(IO.readln());
        double tal3 = Double.parseDouble(IO.readln());
        double average = (tal1 + tal2 + tal3) / 3.0;
        IO.println("The average is " + average);

        //Alt1
        double max = Math.max(Math.max(tal1, tal2), tal3);

        //Alt2
        if (tal1 > tal2 && tal1 > tal3) {
            IO.println("The max is " + tal1);
        } else if (tal2 > tal1 && tal2 > tal3) {
            IO.println("The max is " + tal2);
        } else
            IO.println("The max is " + tal3);

        //Alt3
        //Skapar Array för nummer som blir inmatade
        double[] numbers = {tal1, tal2, tal3};

        //Summan i Array
        double sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }

        double avg = sum / numbers.length;
        IO.println("The average is " + avg);
        //Hitta största via loop
        double storst = numbers[0];

        for (int i = 0; i < numbers.length; i++)    {
            if (numbers[i] > storst) {
                storst = numbers[i];
            }
        }


    }
}
