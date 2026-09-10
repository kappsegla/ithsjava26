package com.example.java26.exercises.week3;

import java.util.Arrays;

public class Exercise4 {
    static void main() {
        IO.println("Skriv ett ord:");
        String userInput = IO.readln();
        String result = "";

        //här loopar den så länge input INTE är . eller blankt
        while (!userInput.equals(".") && !userInput.isEmpty()) {
            result = result + " " + userInput;
            IO.println("Skriv ett ord:");
            userInput = IO.readln();
        }
        IO.println(result);
//-----------------------------
        boolean proceed = true;
        String string = "";

        while (proceed) {
            String input = IO.readln("Write a word: ");
            if (input.isBlank())
                proceed = false;
            else if (input.equals(".")) {
                string = string + input;
                proceed = false;
            } else if (string.isEmpty())
                string = input;
            else
                string = string + " " + input;
        }

        IO.println(string);
        //-----------------------------
        String[] text = {};
        for (int i = 0; i < (text.length + 1); i++) {
            IO.println();
            String[] newWord = Arrays.copyOf(text, (text.length + 1));
            newWord[i] = IO.readln("Skriv ett ord/sträng: ");

            if (newWord[i].isEmpty() || newWord[i].equals("."))
                return;

            else {
                text = Arrays.copyOf(newWord, newWord.length);
//                IO.print("".join(" ", text));
                for (int j = 0; j < text.length; j++)
                    IO.print(text[j] + " ");
            }
        }
    }
}
