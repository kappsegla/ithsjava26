package com.example.java26.functional;

import java.util.List;

public class PStream {


    static void main() {
        var numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);

        var result = numbers.stream()
                .parallel()
                .map(i-> i << 1)
                .toList();
        result.forEach(System.out::println);
    }

//    static void main() {
//        var numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
//
//        try (var scope = StructuredTaskScope.open()) {
//            var task1 = scope.fork(() -> process(numbers, 0, 9));
//            var task2 = scope.fork(() -> process(numbers, 10, 19));
//
//            scope.join();
//
//            var result1 = task1.get();
//            var result2 = task2.get();
//            List<Integer> target = new ArrayList<>(numbers.size());
//            target.addAll(result1);
//            target.addAll(result2);
//
//            target.forEach(IO::println);
//
//        } catch (ExecutionException e) {
//            throw new RuntimeException(e);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static List<Integer> process(List<Integer> numbers, int start, int end) {
//        var result = new ArrayList<Integer>(end - start + 1);
//        for (int i = start; i <= end; i++) {
//            result.add(numbers.get(i) << 1);
//        }
//        return result;
//    }
}
