package com.example.java26.oop;

public class CounterDemo {

    static void main() {
        Counter counter = new Counter();

        counter.increment();
        counter.increment();
        counter.increment();
        counter.decrement();

        Counter counter2 = new Counter();

        Counter counter3 = new Counter(10);

        IO.println(counter.getCounter());
        IO.println(counter2.getCounter());
        IO.println(Counter.getInstances());
    }
}
