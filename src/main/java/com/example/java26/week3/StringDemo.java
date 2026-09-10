package com.example.java26.week3;

public class StringDemo {
    static void main() {

        String name = "Kalle";  //" literals can be used to create String objects
        name = name + " Anka";  //+ operator can be used with String
        String duck = "Kalle";
        String duck2 = "Kalle";

        IO.println(duck == duck2);
        String duckFromKeyBoard = IO.readln("Enter a duck name:");
        //To compare the characters in two different String objects use equals method
        IO.println("Compare from keyboard: " + (duck.equals(duckFromKeyBoard)));
        IO.println("Compare from keyboard: " + (duck.compareTo(duckFromKeyBoard) == 0));

        IO.println(name);
        var lowerCase = name.toLowerCase();
        var length = name.length();
        IO.println(lowerCase);
        IO.println(length);

        for (int i = 0; i < name.length(); i++) {
            IO.println(name.charAt(i));
        }

        String choice = IO.readln("Enter e or p");
        switch (choice.toLowerCase()) {
            case "e" -> IO.println("You picked eternal pain");
            case "p" -> IO.println("You picked pain");
        }

        if (choice.isEmpty()) IO.println("You picked empty pain");
        if (choice.startsWith("e"))
            IO.println("Your choice starts with an e");


    }
}
