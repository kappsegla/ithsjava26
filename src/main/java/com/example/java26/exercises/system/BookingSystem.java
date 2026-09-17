package com.example.java26.exercises.system;

public class BookingSystem {
    static void main() {
        Office office = new Office();
        office.addRoom(new Room("404 Not Found",10));
        office.addRoom(new Room("Dustbin",6));
        office.addRoom(new Room("Calmar",4));
        office.addRoom(new Room("Forest",20));

        Room room = office.findAvailableRoom(5);
        IO.println("Available Room: " + room.getRoomName());
        if( room.book() )
            IO.println("Booking Successful");
        else
            IO.println("Booking Failed");

        room = office.findAvailableRoom(10);
        IO.println("Available Room: " + room.getRoomName());
        if( room.book() )
            IO.println("Booking Successful");
        else
            IO.println("Booking Failed");

        //Skriv ut bokade rum
        IO.println("Booked Rooms: ");
        var listOfBookedRooms = office.getBookedRooms();
        for (Room r : listOfBookedRooms) {
            IO.println(r.getRoomName());
        }
        //Skriv ut lediga rum
        IO.println("Available Rooms: ");
        var listOfAvailableRooms = office.getAvailableRooms();
        for (Room r : listOfAvailableRooms) {
            IO.println(r.getRoomName());
        }

    }
}
