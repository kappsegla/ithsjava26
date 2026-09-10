package com.example.java26.exercises.week3;

import java.util.Arrays;

public class Exercise7 {
    static void main() {
        int[] numbers = {5, 12, 8, 21, 3};
        int sum = 0;
        int max = Integer.MIN_VALUE;  //Alt: numbers[0]
        int min = Integer.MAX_VALUE;

        for (int number : numbers) {
            sum += number;
            if( number > max) max = number; //Alt: max = Math.max(max, number);
            if( number < min) min = number;
        }
        //Modern java solution to the problem
        //var sumWithStream = Arrays.stream(numbers).summaryStatistics();

        IO.println("The sum is " + sum);
        IO.println("The max is " + max);
        IO.println("The min is " + min);
    }
}
