package com.example.java26.exercises.week3;

public class Calc {
    static void main() {
        IO.println(sum(2, 2));
        IO.println(sum(2, 2, 2));
        IO.println(sum(new int[]{2, 2, 2, 2}));
        IO.println(sum("22"));
    }

    static int sum(int a, int b) {
        return a + b;
    }

    static int sum(int a, int b, int c) {
        return a + b + c;
    }

    static int sum(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
        //Alt
        // return Arrays.stream(nums).sum();
    }

    static int sum(String numbers) {
        int sum = 0;
        for (int i = 0; i < numbers.length(); i++) {
            if (Character.isDigit(numbers.charAt(i)))
                //Alt    sum += Integer.parseInt(numbers.substring(i, i + 1));
                //Alt sum += numbers.charAt(i) - '0';
                sum += Character.getNumericValue(numbers.charAt(i));
        }
        //Alt without loop
//        sum = numbers.chars()
//                .filter(Character::isDigit)
//                .map(Character::getNumericValue)
//                .sum();
        return sum;
    }
}
