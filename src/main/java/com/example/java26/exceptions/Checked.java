package com.example.java26.exceptions;

import java.util.stream.Stream;

public class Checked {
    static void main() {

        Stream.of("1", "2", "3", "fyra")
                .map(Checked::convertAndCatch)
                .forEach(System.out::println);
    }

    public static Integer convertAndCatch(String text) {
        try {
            return convert(text);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static Integer convert(String text) throws Exception {
        return Integer.parseInt(text);
    }
}
