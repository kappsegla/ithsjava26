package com.example.java26.oop;

public class IntegerLinkedList {
    private Node head;
    private int counter;

    public void add(int value) {
        if (head == null) {
            Node node = new Node();
            node.value = value;
            head = node;
            counter++;
        }
        else {
            //Hitta sista node objektet
            Node temp = head;
            while( temp.next != null) {
                temp = temp.next;
            }
            //Skapa ny node och lägg till sist
            Node newNode = new Node();
            newNode.value = value;
            temp.next = newNode;
            counter++;
        }
    }

    public void removeAtIndex(int index) {

    }

    public int size() {
        return counter;
    }

    public int getValue(int index) {
        checkIndex(index);

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= counter) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }

    public void removeLast() {

    }

    public void addFirst(int value) {

    }

    public void sort() {

    }

    class Node {
        int value;
        Node next;
    }
}
