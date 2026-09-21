package com.example.java26.oop2;

public class Demo {

    static void main() {
        Object text = "This is a String";
        Object trueOrFalse = true;
        Object value = 1;
        Object pie = new Circle(12.5);

        Circle circle = new Circle(12.5);
        IO.println(circle.hashCode());
        Circle circle2 = new Circle(12.5);
        IO.println(circle2.hashCode());

        if(circle.equals(circle2)){
            IO.println("Same same content");
        }
        if( circle == circle2){
            IO.println("Same object");
        }
        IO.println(circle);
        IO.println(circle.presentYourself());
        IO.println("Circle with radius " + circle.getRadius());
    }
}
