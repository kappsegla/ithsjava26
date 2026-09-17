package com.example.java26.exercises.week4;

public class Ticket {
    private String eventName;

    public Ticket() {
        this.eventName = "Unknown";
    }

    public Ticket(String eventName) {
        this.eventName = eventName;
    }

    static void main() {
        Ticket ticket = new Ticket();
    }
}
