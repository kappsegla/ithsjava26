package com.example.java26.functional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExceptionsInStream2 {
    static void main() {
        List<String> rawNumbers = List.of("10", "20", "foo", "30");

        Map<Boolean, List<Either<String, Integer>>> result = rawNumbers.stream()
                .map(s -> parseToInt(s))
                .collect(Collectors.partitioningBy(Either::isRight));

        //Right values
        result.get(true).stream()
                .map(Either::getRight)
                .flatMap(Optional::stream)
                .forEach(IO::println);

        //Left values
        result.get(false).stream()
                .flatMap(e -> switch (e) {
                    case Either.Right(_) -> Stream.empty();
                    case Either.Left(var value) -> Stream.of(value);
                })
                .forEach(IO::println);
    }

    static Either<String, Integer> parseToInt(String s) {
        try {
            return new Either.Right<>(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return new Either.Left<>("Error when converting " + s);
        }
    }

    public sealed interface Either<L, R> permits Either.Left, Either.Right {
        record Left<L, R>(L value) implements Either<L, R> {
        }

        record Right<L, R>(R value) implements Either<L, R> {
        }

        default Optional<R> getRight() {
            return this instanceof Right<L, R>(R value) ? Optional.of(value) : Optional.empty();
        }

//        default Optional<L> getLeft() {
//            return this instanceof Left<L, R>(L value) ? Optional.of(value) : Optional.empty();
//        }

        default boolean isRight() {
            return this instanceof Right;
        }
    }
}
