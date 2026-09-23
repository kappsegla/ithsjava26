package com.example.java26.composition;

public class House {
    //Composition. Prefer if possible over inheritance
    Room room1 = new Room();
    Room room2 = new Room();
    Kitchen kitchen1 = new Kitchen();
    BathRoom bathroom1 = new BathRoom();
}

class Room {

}

class Kitchen {

}

class BathRoom {

}
