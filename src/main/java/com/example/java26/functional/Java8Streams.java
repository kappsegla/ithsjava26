package com.example.java26.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Java8Streams {


    static void main() {
        List<Book> books = new ArrayList<>();

        books.add(new Book("Clean Code", "Robert Martin", 464));
        books.add(new Book("Effective Java", "Joshua Bloch", 416));
        books.add(new Book("Dune", "Frank Herbert", 604));
        books.add(new Book("The Hobbit", "J.R.R. Tolkien", 310));

        var result = books.stream()
                .filter(book -> book.pages() > 400)
                .map(book -> book.title())
                .collect(Collectors.toSet());

        result.forEach(System.out::println);
    }
}

record Book(
        String title,
        String author,
        int pages) {
}
