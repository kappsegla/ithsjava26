package com.example.java26.arrays;

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
        int[] ar = {10};
      //  int[] br = ar;  //Will not make a copy of the array
        int[] br = new int[ar.length];
        br[0] = ar[0];

        IO.println(ar[0]);
        IO.println(br[0]);
        br[0] = 20;
        IO.println(ar[0]);
        IO.println(br[0]);




    }
}
