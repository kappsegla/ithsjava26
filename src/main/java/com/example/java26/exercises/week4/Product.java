package com.example.java26.exercises.week4;

public class Product {
    private String name;
    private double price;
    private int stockQuantity;

    public Product(String name, double price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity < 0)
            stockQuantity = 0;
        this.stockQuantity = stockQuantity;
    }

    public double totalValue() {
        return price * stockQuantity;
    }

    static void main() {
        Product p1 = new Product("P1", 1000, 1);
        Product p2 = new Product("P2", 2000, 2);
        Product p3 = new Product("P3", 3000, 3);

        IO.println("Name\tPrice\tQuantity\tTotal Value");
        IO.println("----------------------------");
        IO.println(p1.name +"\t\t" + p1.price + "\t" + p1.stockQuantity + "\t" + p1.totalValue());
        IO.println(p2.name + "\t\t" + p2.price + "\t" + p2.stockQuantity + "\t" + p2.totalValue());
        IO.println(p3.name + "\t\t" + p3.price + "\t" + p3.stockQuantity + "\t" + p3.totalValue());
        IO.println("----------------------------");
        IO.println("Total value\t\t\t" + (p1.totalValue() + p2.totalValue() + p3.totalValue()));
    }
}
