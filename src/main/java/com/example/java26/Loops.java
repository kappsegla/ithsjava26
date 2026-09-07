package com.example.java26;

public class Loops {

    static void main() {
        //Print 10 20 30 .. 100
        for (int i = 1; i < 11; i++) {
            IO.println(i * 10);
        }
        for (int i = 10; i < 101; i = i + 10) {
            IO.println(i);
        }

        //Print 10 9 8 .. 0
        for (int i = 10; i >= 0; i--) {
            IO.println(i);
        }
        for (int i = 0; i <= 10; i++) {  //Inkluderande gräns, 10 ingår i serien
            IO.println(10 - i);
        }
        for (int i = 0; i < 11; i++) {  //Exkluderande gräns, 11 ingår inte i serien
            IO.println(10 - i);
        }

        for( char c = 'a'; c <= 'z'; c++ ) {
            IO.println(c + "(" + (int) c + ")");
        }

        for (int i = 0; i < 10; i++) {
            IO.println(i);
        }

        int i = 0;
        while(i < 10) {
            IO.println(i);
            i++;
        }

        for(int j = 0; j < 10; j++)
        {
            if( j % 2 == 0) {
                continue;
            }
            else if(j > 5)
                break;
            System.out.println(j);
        }



    }
}
