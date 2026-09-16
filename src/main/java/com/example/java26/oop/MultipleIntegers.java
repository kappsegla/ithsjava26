package com.example.java26.oop;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class MultipleIntegers {
    private int[] values = new int[10];
    private int counter = 0;

    public void add(int value) {
        if (counter >= values.length) {
            growArray();
        }
        values[counter++] = value;
    }

    public void addFirst(int value) {
        if (counter >= values.length) {
            growArray();
        }
        //Flytta alla värden ett steg höger
        for (int i = counter - 1; i >= 0; i--) {
            values[i + 1] = values[i];
        }
        values[0] = value;
        counter++;
    }

    private void growArray() {
        values = Arrays.copyOf(values, values.length * 2);

//        //Ny större array
//        int[] temp = new int[values.length * 2];
//        //Kopiera från original arrayen till nya större
//        for (int i = 0; i < values.length; i++) {
//            temp[i] = values[i];
//        }
//        //Sätt values att referera till den nya arrayen
//        values = temp;
    }

    public int getValue(int index) {
        //Todo: Error when index is > number of values stored
        return values[index];
    }

    public void removeLast() {
        counter--;
    }

    public void removeAtIndex(int index) {
        //Alla värden efter index flyttas ett steg vänster
        for (int i = index; i < counter - 1; i++) {
            values[i] = values[i + 1];
        }
        //Minska counter med 1
        counter--;
    }

    public int size() {
        return counter;
    }

    public void sort(){
        var copy = Arrays.copyOfRange(values, 0, counter);
        bogoSort(copy);
        values = copy;
    }

    private void bogoSort(int[] values) {
        while( notSorted(values) )
            shuffle(values);
    }

    private void shuffle(int[] values) {
        //Byt plats på två slumpmässiga platser x antal ggr.
        Random random = new Random();
        int rounds = random.nextInt(values.length, values.length * 2);
        for (int i = 0; i < rounds; i++) {
            int indexA = random.nextInt(values.length);
            int indexB = random.nextInt(values.length);

            int temp = values[indexA];
            values[indexA] = values[indexB];
            values[indexB] = temp;
        }
    }

    private boolean notSorted(int[] values) {
        for (int i = 0; i < values.length - 1; i++) {
            if (values[i] > values[i + 1]) {
                return true;
            }
        }
        return false;
    }

    static void main() {
        MultipleIntegers integers = new MultipleIntegers();
        integers.add(100);
        integers.add(99);
        integers.add(1);
        integers.add(20);
        integers.add(30);
        integers.add(5);
        integers.add(5);
        integers.add(40);
        integers.add(20);
        integers.add(60);
//        integers.removeLast();
        integers.removeAtIndex(1);
        integers.addFirst(99);
        integers.add(25);
//        IO.println(integers.getValue(0));
//        IO.println(integers.getValue(3));
        //   IO.println(integers.getValue(9));  //Nothing stored here
        //   IO.println(integers.getValue(10)); //Gives error...
        integers.sort();
        for (int i = 0; i < integers.size(); i++) {
            IO.println(integers.getValue(i));
        }

    }
}
