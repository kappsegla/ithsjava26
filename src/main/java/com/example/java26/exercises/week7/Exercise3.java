package com.example.java26.exercises.week7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Exercise3 {

    public static <T> List<T> reversedList(List<T> original) {
        List<T> reversed = new ArrayList<>();
        for (int i = original.size() - 1; i >= 0; i--) {
            reversed.add(original.get(i));
        }
        return reversed;
    }

    public static <T> List<T> reversedList2(List<T> original) {
        return new ArrayList<>(original.reversed());
    }

    public static <T> List<T> reverse(List<T> original) {
        List<T> reversed = new ArrayList<>(original);
        Collections.reverse(reversed);
        return reversed;
    }

    static void main() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        integers.reversed().forEach(System.out::println);

    }


}
