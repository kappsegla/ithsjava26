package com.example.java26.exercises.week3;

public class Exercise9 {
    static void main() {
        String input = IO.readln("Enter some text: ");
        IO.println("Number of characters: " + input.length());
        //IO.println("Number of unicode characters: " + input.codePointCount(0, input.length()));
        IO.println(input.toUpperCase());
        String reversed = new StringBuilder(input).reverse().toString();
        IO.println(reversed);
        //Alt
        for (int i = input.length() - 1; i >= 0; i--) {
            IO.print(input.charAt(i));
        }
        IO.println();
        IO.println(input.contains("Java"));
    }
}
