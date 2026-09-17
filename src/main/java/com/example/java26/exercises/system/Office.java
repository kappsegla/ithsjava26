package com.example.java26.exercises.system;

import java.util.ArrayList;

public class Office {
    //Room[] rooms = new Room[4];
    private ArrayList<Room> rooms;
    private static int totalRooms = 0;

    public Office() {
        rooms = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
        totalRooms++;
    }

    public ArrayList<Room> getBookedRooms() {
        var bookedRooms = new ArrayList<Room>();
        for (Room r : rooms) {
            if (r.isBooked()) {
                bookedRooms.add(r);
            }
        }
        return bookedRooms;
    }

    public ArrayList<Room> getAvailableRooms() {
        var availableRooms = new ArrayList<Room>();
        for (Room r : rooms) {
            if (!r.isBooked()) {
                availableRooms.add(r);
            }
        }
        return availableRooms;
    }

    public Room findAvailableRoom(int minCapacity) {
        for (Room room : rooms) {
            if (room.getCapacity() >= minCapacity && !room.isBooked()) {
                return room;
            }
        }
        return null;
    }
}
