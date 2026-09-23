package com.example.java26.oop2.animal;

import com.example.java26.oop2.auth.Authenticator;

public class Dog extends Mammal implements Pet {
    @Override
    public String makeSound(){
         return "Woof!";
    }

    @Override
    public boolean isCute() {
        return true;
    }
}
