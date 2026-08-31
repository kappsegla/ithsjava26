package com.example.java26;

public class Input {
    static void main() {
        IO.println("Vad heter du?");
        String name = IO.readln();
        IO.println("Hej " + name);
        IO.println("Hur många katter har du?");
        int catCount = Integer.parseInt(IO.readln());
        if (catCount > 0)
            IO.println("Du har " + catCount + " katt(er)");
        IO.println("Om du skaffar en katt till har du " + (catCount + 1));
//        IO.println("Om du skaffar en katt till har du " + ++catCount );
//        var temp = catCount + 1;
//        IO.println("Om du skaffar en katt till har du " + temp );
        int age = Integer.parseInt(IO.readln("What is your age?"));
        if (age < 18) {
            IO.println("You must be over 18");
            return;
        }
        IO.println("Only for over 18 code");
    }
}
