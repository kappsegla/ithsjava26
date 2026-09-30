package com.example.java26.oop;

import org.w3c.dom.Node;

public class IntegerLinkedList {
    private Node head;
    private int counter;

    public void add(int value) {
        if (head == null) {
            Node node = new Node();
            node.value = value;
            head = node;
            counter++;
        } else {
            //Hitta sista node objektet
            Node temp = head;
            while (temp.next != null) {
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
        if (counter == 0) {
            return;
        }
        if (counter == 1 && index == 0) {
            head = null;
        }
        else if(index == 0) {
            head = head.next;
        }
        else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
        counter--;

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
        if (counter == 0) {
            return;
        }

        if (counter == 1) {
            head = null;
        } else {
            Node current = head;
            while (current.next.next != null) {
                current = current.next;
            }
            current.next = null;
        }
        counter--;
    }

    public void addFirst(int value) {
        Node node = new Node();
        node.value = value;
        node.next = head;
        head = node;
        counter++;
    }

    class Node {
        int value;
        Node next;
    }
}
