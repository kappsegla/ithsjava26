package com.example.java26;

public class CliApp {
    static void main() {
        String menuText = """
                Menu
                --------------
                1. Say hello
                2. Say goodbye
                3. Exit                               
                """;
        boolean running = true;
        do {
            IO.println(menuText);
            String choice = IO.readln("Pick a number:");
            switch (choice) {
                case "1" -> IO.println("Hello");
                case "2" -> IO.println("Goodbye");
                case "3" -> running = false;
            }
        } while (running);
    }
}
