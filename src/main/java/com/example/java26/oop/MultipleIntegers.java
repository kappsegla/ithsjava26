package com.example.java26.oop;

public class MultipleIntegers {
    private int[] values = new int[10];
    private int counter = 0;

    public void add(int value){
        //Todo: Handle the case where values is full. counter > values.length - 1
        values[counter++] = value;
    }

    public int getValue(int index){
        //Todo: Error when index is > number of values stored
        return values[index];
    }

    public void removeLast(){
        counter--;
    }

    public int size() {
        return counter;
    }

    static void main() {
        MultipleIntegers integers = new MultipleIntegers();
        integers.add(100);
        integers.add(10);
        integers.add(10);
        integers.add(20);
        integers.removeLast();
        integers.add(25);
        IO.println(integers.getValue(0));
        IO.println(integers.getValue(3));
     //   IO.println(integers.getValue(9));  //Nothing stored here
     //   IO.println(integers.getValue(10)); //Gives error...

        for (int i = 0; i < integers.size(); i++) {
            IO.println(integers.getValue(i));
        }

    }
}
