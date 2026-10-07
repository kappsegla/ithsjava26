package com.example.java26.generics;

import java.util.Arrays;
import java.util.Objects;

public class MyHashMap<K, V> {
    private final int DEFAULT_CAPACITY = 16;
    private final float LOAD_FACTOR = 0.75f;
    private Node[] buckets = new Node[DEFAULT_CAPACITY];
    private int size = 0;

    public V put(K key, V value) {
        int index = getIndex(key);
        var head = buckets[index];

        Node<K, V> currentNode = head;
        while (currentNode != null) {
            if (Objects.equals(currentNode.key, key)) {
                //Same key
                var oldValue = currentNode.value;
                currentNode.value = value;
                return oldValue;
            }
            currentNode = currentNode.next;
        }

        buckets[index] = new Node(key, value, head);
        size++;

        if (size > buckets.length * LOAD_FACTOR)
            resize();
        return null;
    }

    public V get(K key) {
        int index = getIndex(key);
        Node<K, V> current = buckets[index];
        while (current != null) {
            if (Objects.equals(current.key, key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public int size() {
        return size;
    }

    public void clear() {
        size = 0;
        //buckets = new Node[DEFAULT_CAPACITY];
        Arrays.fill(buckets, null);
    }

    private int getIndex(K key) {
        if (key == null)
            return 0;
        return Math.abs(key.hashCode()) % buckets.length;
    }

    private void resize() {
        //Save ref to old buckets
        Node<K, V>[] oldBuckets = buckets;
        //Create new bucket with double size
        buckets = new Node[buckets.length * 2];
        size = 0;

        //Loop through oldBuckets and add with put(key, value)
        //Remember to check for next links
        for (Node<K, V> headNode : oldBuckets) {
            var current = headNode;
            while (current != null) {
                //Rehashing when adding to the new larger bucket array
                put(current.key, current.value);
                current = current.next;
            }
        }
    }

    static class Node<K, V> {
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
        String s3 = "Cc";

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
