package com.example.java26.exercises.booking;

public class Room {
    private String roomName;
    private int capacity;
    private boolean isBooked;
    public Room(String roomName, int capacity) {
        this.roomName = roomName;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public boolean book(){
        //If not booked yet, book it and return true. Return false if it already was booked.
        if( !isBooked ) {
            isBooked = true;
            return true;
        }
        return false;
    }

    public void cancelBooking(){
        isBooked = false;
    }
}
