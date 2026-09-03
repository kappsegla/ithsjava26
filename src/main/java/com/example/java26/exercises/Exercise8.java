package com.example.java26.exercises;

public class Exercise8 {

    static void main() {
        int a =  10;//Integer.parseInt(IO.readln());
        int b = 20;//Integer.parseInt(IO.readln());

        IO.println(a + " " + b );

        int temp = a;
        a = b;
        b = temp;

        IO.println(a + " " + b );
    }
}
