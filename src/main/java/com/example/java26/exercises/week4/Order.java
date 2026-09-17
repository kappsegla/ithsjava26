package com.example.java26.exercises.week4;

import java.util.concurrent.atomic.AtomicInteger;

public class Order {
    //static AtomicInteger oNumber = new AtomicInteger(1000);
    static int nextOrderNumber = 1000;
    private int orderNumber;

    public Order() {
        //oNumber.getAndAdd(1);
        orderNumber = nextOrderNumber++;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public int getNextOrderNumber() {
        return nextOrderNumber;
    }

    static void main() {
        Order order1 = new Order();
        IO.println(order1.getOrderNumber());
        Order order2 = new Order();
        IO.println(order2.getOrderNumber());
        Order order3 = new Order();
        IO.println(order3.getOrderNumber());
        Order order4 = new Order();
        IO.println(order4.getOrderNumber());
    }
}
