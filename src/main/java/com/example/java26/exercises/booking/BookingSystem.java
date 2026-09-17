package com.example.java26.exercises.booking;

public class BookingSystem {

    static void main() {
        Office office = new Office();
        office.addRoom(new Room("The Panic Room", 4));
        office.addRoom(new Room("404 Not Found", 10));
        office.addRoom(new Room("The Batcave", 8));
        office.addRoom(new Room("The Death Star", 20));

        String menu = """
                Booking system
                ==============
                1. Book a room
                2. Available rooms
                3. Booked rooms
                e. Exit
                """;

        while (true) {
            IO.println(menu);
            var choice = IO.readln("C'mon, do something... ");
            switch (choice) {
                case "1" -> bookARoom(office);
                case "2" -> listAvailableRooms(office);
                case "3" -> listBookedRooms(office);
                case "e","E" -> System.exit(0);
                default -> IO.println("That's not something!");
            }
        }
    }

    private static void listBookedRooms(Office office) {

    }

    private static void listAvailableRooms(Office office) {

    }

    private static void bookARoom(Office office) {
        int capacity = Integer.parseInt(IO.readln("How many seats do you need? "));


    }
}
