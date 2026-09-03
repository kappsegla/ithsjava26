package com.example.java26.exercises;

public class Exercise6 {

    static void main() {
        int tal = Integer.parseInt(IO.readln());

        if (tal > 0)
            IO.println("Talet är större än 0");
        else if (tal < 0)
            IO.println("Talet är mindre än 0");
        else
            IO.println("Talet är 0");
    }
}
