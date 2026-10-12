package com.example.java26.functional;

import java.util.ArrayList;
import java.util.List;

public class PStream {


    static void main() {
        var numbers = List.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20);

        var result1 = process(numbers, 0,9);
        var result2 = process(numbers, 10,19);

        List<Integer> target = new ArrayList<>(numbers.size());
        target.addAll(result1);
        target.addAll(result2);

        target.forEach(IO::println);
    }

    public static List<Integer> process(List<Integer> numbers, int start, int end) {
        var result = new ArrayList<Integer>(end - start + 1);
        for(int i = start; i <= end; i++) {
            result.add(numbers.get(i) << 1);
        }
        return result;
    }
}
