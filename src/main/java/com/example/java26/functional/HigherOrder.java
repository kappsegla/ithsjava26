package com.example.java26.functional;

import java.util.List;
import java.util.function.Consumer;

public class HigherOrder {

    static void main() {
        var colors = List.of("red", "green", "blue");

        //Imperativ stil
        for (int i = 0; i < colors.size(); i++) {
            var color = colors.get(i);
            if (color.contains("r"))
                IO.println(i + ". " + color);
        }

        //Functional style using forEach
        colors.forEach(text -> IO.println(text));  //Lambda implementation
        colors.forEach(IO::println); //Method reference

        //Java 8 Streams
        var count = colors.stream()
                .filter(color -> color.contains("r"))
                .count();
        IO.println("Number of colors with r in the name: " + count);

        colors.stream()
                .filter(color -> color.contains("r"))
                .forEach(IO::println);

        colors.stream()
                .filter(color -> color.contains("r"))
                .limit(2)
                .forEach(IO::println);
    }

    public static Consumer<String> createConsumer() {
        return s -> IO.println(s);
    }
}
