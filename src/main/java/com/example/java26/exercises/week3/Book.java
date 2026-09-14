package com.example.java26.exercises.week3;

import java.time.Year;
import java.util.Objects;

public final class Book {
    private final String title;
    private final String author;
    private final Year year;

    public Book(String title, String author, Year year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String title() {
        return title;
    }

    public String author() {
        return author;
    }

    public Year year() {
        return year;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Book) obj;
        return Objects.equals(this.title, that.title) &&
                Objects.equals(this.author, that.author) &&
                Objects.equals(this.year, that.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, year);
    }

    @Override
    public String toString() {
        return "Book[" +
                "title=" + title + ", " +
                "author=" + author + ", " +
                "year=" + year + ']';
    }
}
