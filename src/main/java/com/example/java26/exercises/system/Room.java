package com.example.java26.exercises.system;

public class Room {
    private String roomName;
    private int capacity;
    private boolean isBooked;

    public Room(String roomName, int capacity) {
        this.roomName = roomName;
        this.capacity = capacity;
        this.isBooked = false;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean book() {
        if (isBooked) {
            return false;
        }
        isBooked = true;
        return true;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void cancelBooking() {
        isBooked = false;
    }
}
