package com.example.java26.oop;

public class PersonDemo {
    static void main() {
        Person person1 = new Person("Anna", 28);
        IO.println("Namn: " + person1.getName() + ", Ålder: " + person1.getAge());

        // För att ändra ett värde skapas ett nytt objekt eftersom Person är immutable
        Person person2 = person1.withAge(29);
        IO.println("Ursprunglig person: " + person1);
        IO.println("Uppdaterad person: " + person2);

        Person person3 = person2.withName("Anna Andersson");
        IO.println("Person med nytt namn: " + person3);
    }
}
