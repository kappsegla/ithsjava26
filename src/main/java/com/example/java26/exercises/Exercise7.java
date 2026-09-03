package com.example.java26.exercises;

public class Exercise7 {
    static void main() {
        int tal = Integer.parseInt(IO.readln());

        if( tal % 2 == 0)  //Even numbers gives 0 as rest with modulo
            IO.println("YES");
        else                //tal % 2 == 1 Odd numbers
            IO.println("NO");

        if( !isEven(tal))  //True if odd
            IO.println("YES");
    }

    public static boolean isEven(int a) {
        return a % 2 == 0;
    }

    public static boolean isOdd(int a) {
        return !isEven(a);
    }
}
