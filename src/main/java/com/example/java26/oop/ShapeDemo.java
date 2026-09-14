package com.example.java26.oop;

public class ShapeDemo {
    static void main() {
        Rectangle rectangle = new Rectangle();

        //rectangle.setHeight(10);
        IO.println(rectangle.getHeight());

        Rectangle rectangle2 = new Rectangle(10, 10, new Color(255, 255, 255));
        Rectangle rectangle3 = new Rectangle(100, 100, new Color(255, 255, 255));

        IO.println(rectangle.getColor());

        Color color = rectangle.getColor();
        IO.println(color.red());

        Rectangle rectangle4 = new Rectangle(rectangle2);
        IO.println(rectangle2.getWidth());
        IO.println(rectangle4.getWidth());
        rectangle4.setWidth(50);
        IO.println(rectangle2.getWidth());
        IO.println(rectangle4.getWidth());

        Rectangle rectangle5 = rectangle2.copyOf(rectangle2);
        Rectangle rectangle6 = rectangle2.copyOf();




    }
}
