package com.example.java26.exercises.week4;

public class Employee {

    private String name;
    private double salary;
    private String department;

    //Konstruktor för namn, lön samt område
    public Employee(String name, double salary, String department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    //Konstruktor för namn och lön
    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
        this.department = "Ej tilldelad";
    }

    //Getters och setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    //Copy constructor
    public Employee(Employee copy) {
        this.name = copy.name;
        this.salary = copy.salary;
        this.department = copy.department;
    }

    //Utskrift av info
    public String info() {
        return "Name: " + name + ", Salary: " + salary + ", Department: " + department;
    }

    public static void main(String[] args) {

        //Två med konstruktor för tre värden
        Employee employee1 = new Employee("Björn", 35000, "Lärare");
        Employee employee2 = new Employee("Maria", 40000, "Lärare");
        IO.println(employee1.info());
        IO.println(employee2.info());
        IO.println();

        //Två med konstruktor för två värden
        Employee employee3 = new Employee("Anna", 37000);
        Employee employee4 = new Employee("Cesar", 41000);
        IO.println(employee3.info());
        IO.println(employee4.info());
        IO.println();

        //Två med copy-constructor
        Employee employee5 = new Employee(employee1);
        Employee employee6 = new Employee(employee3);
        IO.println(employee5.info());
        IO.println(employee6.info());
    }
}
