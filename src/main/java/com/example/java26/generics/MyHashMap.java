package com.example.java26.generics;

public class MyHashMap<K,V> {
    private final int DEFAULT_CAPACITY = 16;
    private final float LOAD_FACTOR = 0.75f;
    Node[] buckets = new Node[DEFAULT_CAPACITY];
    private int size = 0;

    public void put(K key, V value) {
        int index = getIndex(key);
        var head = buckets[index];

        var currentNode = head;
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                //Same key
                currentNode.value = value;
                return;
            }
            currentNode = currentNode.next;
        }

        buckets[index] = new Node(key, value, head);
        size++;

        //Todo: Kolla loadfactor, (size * 1.0 / buckets.length ) > LOAD_FACTOR increase array size
    }

    public V get(K key) {
        int index = getIndex(key);
        Node<K,V> current = buckets[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    private int getIndex(K key) {
        if (key == null)
            return 0;
        return Math.abs(key.hashCode()) % buckets.length;
    }


    static class Node<K,V> {
        K key;
        V value;
        Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }


    static void main() {

        String s1 = "Aa";
        String s2 = "BB";
        String s3 = "CCx";

        MyHashMap<String, String> mh = new MyHashMap<>();
        mh.put(s1, "Value1");
        mh.put(s2, "Value2");
        mh.put(s3, "Value3");

        var value = mh.get("Aa");
        IO.println(value);

        IO.println(s1.hashCode());
        IO.println(s2.hashCode());
        IO.println(s1.equals(s2));
        IO.println(s3.hashCode());
    }


}
