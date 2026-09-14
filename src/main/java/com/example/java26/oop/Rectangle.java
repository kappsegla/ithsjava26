package com.example.java26.oop;

public class Rectangle {
    private int width;   //Field/Fält
    private int height;  //private access, only code in this class
    private Color color;  //Reference to another object, Composition

    public Rectangle(){
        width = 1;
        height = 1;
    }

    public Rectangle(int w, int h){
        width = w;
        height = h;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}

record Color(int red, int green, int blue) {
}
