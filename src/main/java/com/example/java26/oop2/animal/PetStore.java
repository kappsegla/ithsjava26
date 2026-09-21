package com.example.java26.oop2.animal;

import java.util.ArrayList;

public class PetStore {
    static void main() {
        Mammal m = new Mammal();
        m.makeSound();
        Dog d = new Dog();
        d.makeSound();
        Cat c = new Cat();
        c.makeSound();

        Mammal m1 = new Dog();
        m1.makeSound();
        IO.println();
        ArrayList<Mammal> mammals = new ArrayList<>();
        mammals.add(d);
        mammals.add(c);
        mammals.add(m1);
        mammals.add(new Puppy());

        for (Mammal mammal : mammals) {
//            if (mammal instanceof Puppy puppy) {
//                puppy.play();
//            }
            IO.println(mammal.makeSound());
        }
    }
}
