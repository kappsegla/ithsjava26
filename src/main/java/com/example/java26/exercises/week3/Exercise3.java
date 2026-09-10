package com.example.java26.exercises.week3;

public class Exercise3 {
    static void main() {
        System.out.println(countTrue(new boolean[]{true, false, true}));
        System.out.println(countTrue(new boolean[]{true, true, true}));
        System.out.println(countTrue(new boolean[]{}));

    }

    static int countTrue(boolean[] array) {
        int count = 0;

        for (boolean b : array) {
            if (b) {
                count++;
            }
        }
        return count;
    }
}
