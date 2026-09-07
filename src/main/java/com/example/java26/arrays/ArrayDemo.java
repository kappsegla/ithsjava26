package com.example.java26.arrays;

public class ArrayDemo {
    static void main() {
        int[] myArray = new int[10];

        for (int i = 0; i < myArray.length; i++) {
            myArray[i] = i;
            IO.println(myArray[i]);
        }

        int[] myArray2 = {1,2,3,4};
        for (int i = 0; i < myArray2.length; i++) {
            IO.println(myArray2[i]);
        }
    }
}
