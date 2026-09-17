package com.example.java26.exercises.week4;

public class Triangle { //Uppgift 1.2
    private double sideA; //Ska ligga i klassen utanför mainmetoden
    private double sideB;
    private double sideC;

    public Triangle() {}

    public Triangle(double v, double v1, double v2) {
        this.sideA = v;
        this.sideB = v1;
        this.sideC = v2;
    }

    /**
     * Constructs a Triangle where all three sides are equal to the specified side length.
     *
     * @param side the length of all three sides of the triangle
     */
    public Triangle(double side){
        this.sideA = side;
        this.sideB = side;
        this.sideC = side;
    }

    public Triangle(Triangle original){
        this.sideA = original.sideA;
        this.sideB = original.sideB;
        this.sideC = original.sideC;
    }

    public static Triangle copyOf(Triangle original) {
        return new Triangle(original);
    }


    static void main() {
        Triangle triangle = new Triangle(3.0,4.0,5.0);
//        triangle.setSideA(3.0);
//        triangle.setSideB(4.0);
//        triangle.setSideC(5.0);
        IO.println("Perimeter: " + triangle.perimeter());
        IO.println("Type: " + triangle.triangleType());

        Triangle triangle2 = new Triangle();
        triangle2.setSideA(10.0);
        triangle2.setSideB(10.0);
        triangle2.setSideC(10.0);
        IO.println("Perimeter: " + triangle2.perimeter());
        IO.println("Type: " + triangle2.triangleType());

        Triangle triangle3 = new Triangle();
        triangle3.setSideA(20.0);
        triangle3.setSideB(20.0);
        triangle3.setSideC(10.0);
        IO.println("Perimeter: " + triangle3.perimeter());
        IO.println("Type: " + triangle3.triangleType());

        Triangle triangle4 = new Triangle();
        triangle4.setSideA(0.0);
        triangle4.setSideB(0.0);
        triangle4.setSideC(0.0);
        IO.println("Perimeter: " + triangle4.perimeter());
        IO.println("Type: " + triangle4.triangleType());

        Triangle triangle5 = new Triangle(10);
        Triangle triangle6 = new Triangle(triangle5);
        Triangle triangle7 = Triangle.copyOf(triangle6);
    }

    public double getSideA() { //getMetoder ska inte vara static static kan inte läsa instansfält
        return sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public double getSideC() {
        return sideC;
    }

    public void setSideA(double sideA) {
        this.sideA = sideA;
    }

    public void setSideB(double sideB) {
        this.sideB = sideB;
    }

    public void setSideC(double sideC) {
        this.sideC = sideC;
    }

    public double perimeter() {
        return sideA + sideB + sideC;
    }

    public boolean isValidTriangle() {
        return sideA + sideB > sideC && sideA + sideC > sideB && sideB + sideC > sideA;
    }

    public String triangleType() {
        if(!isValidTriangle()) {
            return "Ogiltig triangel";
        } else if (sideA == sideB && sideA == sideC) {
            return "Liksidig triangel";
        } else if (sideA == sideB || sideA == sideC || sideB == sideC) {
            return "Likbent triangel";
        } else {
            return "Oliksidig triangel";
        }
    }

//    public TriangleType triangleType() {
//        if(!isValidTriangle()) {
//            return TriangleType.Ogiltig;
//        } else if (sideA == sideB && sideA == sideC) {
//            return TriangleType.Liksidig;
//        } else if (sideA == sideB || sideA == sideC || sideB == sideC) {
//            return TriangleType.Likbent;
//        } else {
//            return TriangleType.Oliksidig;
//        }
//    }
}

enum TriangleType {
    Ogiltig,
    Liksidig,
    Likbent,
    Oliksidig
}
