package com.example.java26.exercises.week3;

import java.time.Year;

public class Exercise11 {
    static void main() {
        var book = new Book("Programming in Java", "Duke", Year.of(2018));
        String desc = description(book);
        IO.print(desc);
    }

    static String description(Book b){
        return "Title: " + b.title() + " – Author: " + b.author() + " – Published: " + b.year();
    }
}
