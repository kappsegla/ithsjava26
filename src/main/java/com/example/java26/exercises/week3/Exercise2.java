package com.example.java26.exercises.week3;

public class Exercise2 {
    static void main() {
        int i = 65536;

        while (i > 2) {
            IO.println(i);
            i = i >> 1;
        }
        for (int number = 65536; number > 2; number /= 2) // /= --> number = number / 2
            System.out.println(number);
    }
}
