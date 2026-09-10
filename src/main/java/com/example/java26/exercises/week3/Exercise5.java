package com.example.java26.exercises.week3;

public class Exercise5 {
    static void main() {
        int secretNumber = 50;
        int guess = 0;
        int guessCount = 0;

        do {
            guess = getGuess();
            guessCount++;
            if (guess < secretNumber) {
                IO.println("För lågt, försök igen: ");
            }
            //Jämför med hemliga talet
            //Skriv ut om det är för högt
            else if (guess > secretNumber) {
                IO.println("För högt, försök igen: ");
            }
            //Skriv ut om det är rätt och avsluta
            else {
                IO.println("Rätt, bra jobbat!");
                IO.println("Antal försök: " + guessCount);
            }
        } while (guess != secretNumber);
    }

    private static int getGuess() {
        int guess = 0;
        try {
            guess = Integer.parseInt(IO.readln("Guess a number between 1 and 100: "));
        } catch (NumberFormatException e) {
        }
        return guess;
    }
}
