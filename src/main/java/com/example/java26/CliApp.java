package com.example.java26;

public class CliApp {
    static void main() {
        boolean running = true;
        do {
            printMenu();
            String choice = IO.readln("Pick a number:");
            switch (choice) {
                case "1" -> IO.println("Hello");
                case "2" -> IO.println("Goodbye");
                case "3" -> running = false;
            }
        } while (running);
    }

    public static void printMenu() {
        String menuText = """
                Menu
                --------------
                1. Say hello
                2. Say goodbye
                3. Exit                               
                """;
        IO.println(menuText);
    }
}
