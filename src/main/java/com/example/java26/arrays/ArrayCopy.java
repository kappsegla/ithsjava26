package com.example.java26.arrays;

import java.util.Arrays;

public class ArrayCopy {
    static void main() {
        int a = 10;
        int b = a;

        IO.println(a);
        IO.println(b);
        b = 20;
        IO.println(a);
        IO.println(b);
        IO.println("------------");
//---------------------
        int[] ar = {10,20};
      //  int[] br = ar;  //Will not make a copy of the array
//        int[] br = new int[ar.length];
//        for (int i = 0; i < ar.length; i++) {
//            br[i] = ar[i];
//        }

        //Prefer this over using loop for speed and security
        int br[] = Arrays.copyOf(ar, ar.length);
        IO.println(ar[0]);
        IO.println(br[0]);
        br[0] = 20;
        IO.println(ar[0]);
        IO.println(br[0]);




    }
}
