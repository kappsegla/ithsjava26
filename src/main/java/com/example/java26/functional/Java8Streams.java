package com.example.java26.functional;

import java.util.ArrayList;
import java.util.List;

public class Java8Streams {


    static void main() {
        List<Book> books = new ArrayList<>();

        books.add(new Book("Clean Code", "Robert Martin", 464));
        books.add(new Book("Effective Java", "Joshua Bloch", 416));
        books.add(new Book("Dune", "Frank Herbert", 604));
        books.add(new Book("The Hobbit", "J.R.R. Tolkien", 310));

        books.stream()
                .filter(book-> book.pages() > 400)
                .map(book-> book.title())
                .forEach(IO::println);
    }
}

record Book(
        String title,
        String author,
        int pages) {
}
