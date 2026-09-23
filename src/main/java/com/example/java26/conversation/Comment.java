package com.example.java26.conversation;

import java.util.ArrayList;
import java.util.List;

public class Comment {
    private final String author;
    private final String text;
    //private Comment reply;   //Good cases: Can be null, Can be reference to another reply
    //Bad cases: Can point to ourselves, Can point to other that points to us
    private List<Comment> comments = new ArrayList<>();

    public Comment(String author, String text) {
        this.author = author;
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public void addReply(Comment reply) {
        comments.add(reply);
    }

    public void printThread(String indentation) {
        IO.println(indentation + "💬 " + author + ": " + text);
        for (Comment comment : comments) {
            comment.printThread(indentation + "    ");
        }
    }


    static void main() {
        Comment firstComment = new Comment("Martin", "Vad tycker du om java?");

        Comment reply1 = new Comment("Bob", "Jag gillar det! Speciellt typsystemet.");
        Comment reply2 = new Comment("Charlie", "Lite väl mycket boilerplate-kod ibland, men gillar JVM.");
        firstComment.addReply(reply1);
        firstComment.addReply(reply2);

        Comment reply3 = new Comment("Alice", "Håller med dig Bob!");
        reply1.addReply(reply3);

        firstComment.printThread("");
    }
}
