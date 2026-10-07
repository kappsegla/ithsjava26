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

//    public static Map<String, Integer> findMinMax(List<Integer> list) {
//        var min = Collections.min(list);
//        var max = Collections.max(list);
//        return Map.of("min", min, "max", max);
//    }

    //    public static MinMax findMinMax(List<Integer> list) {
//        var min = Collections.min(list);
//        var max = Collections.max(list);
//        return new MinMax(min, max);
//    }
    public static Pair<Integer, Integer> findMinMax(List<Integer> list) {
        var min = Collections.min(list);
        var max = Collections.max(list);
        return new Pair<>(min, max);
    }

//    public static class Pair<T1, T2> {
//        T1 value1;
//        T2 value2;
//
//        public Pair(T1 value1, T2 value2) {
//            this.value1 = value1;
//            this.value2 = value2;
//        }
//
//        @Override
//        public String toString() {
//            return "Pair{" +
//                    "value1=" + value1 +
//                    ", value2=" + value2 +
//                    '}';
//        }
//    }
    public static record Pair<T1,T2>(T1 first, T2 second) {}

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
