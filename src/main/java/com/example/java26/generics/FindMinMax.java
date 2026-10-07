package com.example.java26.generics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindMinMax {


//   public static List<Integer> findMinMax(List<Integer> list){
//       var min = Collections.min(list);
//       var max = Collections.max(list);
//       return List.of(min,max);
//   }

//    public static String findMinMax(List<Integer> list){
//        var min = Collections.min(list);
//        var max = Collections.max(list);
//        return "{\"min\": "+min+",\"max\": "+max+"}";
//    }

    public static MinMax findMinMax(List<Integer> list) {
        var min = Collections.min(list);
        var max = Collections.max(list);
        return new MinMax(min, max);
    }

    record MinMax(int min, int max) {
    }

    static void main() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        var result = findMinMax(list);

        IO.println(result);
    }
}
