package com.example.java26.arrays;

public class Errors {
    static void main() {
        int[] array = {10,20,30};
//        IO.println(array[3]);  //ArrayIndexOutOfBounds
        array = new int[Integer.MAX_VALUE - 8];  //Must save some space for meta data
        //Still not working, OutOfMemoryError:  Java heap space
        //Add VM options -Xms8g -Xmx10g
    }
}
