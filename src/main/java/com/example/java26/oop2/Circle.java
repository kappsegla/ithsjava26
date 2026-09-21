package com.example.java26.oop2;

import java.util.Objects;

public class Circle extends Object {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Circle circle)) return false;

        return Double.compare(radius, circle.radius) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(radius);
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                '}';
    }

    public String presentYourself(){
        return "Circle with radius " + radius;
    }

    //    @Override
//    public int hashCode() {
//        return Objects.hash(radius);
//    }
//
//    @Override
//    public boolean equals(Object obj) {
////        if( obj == null)
////            return false;
////        if( obj == this)
////            return true;
////        if( obj.getClass() != Circle.class)
////            return false;
////        Circle other = (Circle) obj;
////        return  Objects.equals(this.radius, other.radius);
//
//        if( obj instanceof Circle other){
//            return Objects.equals(this.radius, other.radius);
//        }
//        return false;
//    }
}
