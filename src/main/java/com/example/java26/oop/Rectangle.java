package com.example.java26.oop;

public class Rectangle {
    private int width;   //Field/Fält
    private int height;  //private access, only code in this class
    private Color color;  //Reference to another object, Composition. Defaults to null

    public Rectangle() {
        width = 1;
        height = 1;
        color = new Color(0, 0, 0);
    }

    public Rectangle(int width, int height) {
        //Constructor chaining. Moves all validation to constructor that takes all parameters
        this(width, height, new Color(0, 0, 0));
//        if (width < 1 || height < 1)
//            throw new IllegalArgumentException();
//        this.width = width;
//        this.height = height;
//        this.color = new Color(0, 0, 0);
    }

    public Rectangle(int width, int height, Color color) {
        if (width < 1 || height < 1 || color == null)
            throw new IllegalArgumentException();
        this.width = width;
        this.height = height;
        this.color = color;
    }

    //Copy constructor
    public Rectangle(Rectangle rectangle) {
        //this() calls another constructor
        this(rectangle.width, rectangle.height, rectangle.color);
        //Alt: set each field individually
//        this.width = rectangle.width;
//        this.height = rectangle.height;
//        this.color = rectangle.color;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        if (width < 1)
            throw new IllegalArgumentException();
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if( height < 1)
            throw new IllegalArgumentException();
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        if( color == null)
            throw new IllegalArgumentException();
        this.color = color;
    }

    public int area() {
        return width * height;
    }

    public Rectangle copyOf(Rectangle orig) {
        return new Rectangle(orig);
    }

    public Rectangle copyOf() {
        return new Rectangle(this);
    }
}

record Color(int red, int green, int blue) {
}
