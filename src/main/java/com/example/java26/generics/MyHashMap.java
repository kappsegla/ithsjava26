package com.example.java26.generics;

import java.util.HashMap;

public class MyHashMap {
    private final int DEFAULT_CAPACITY = 16;
    private final float LOAD_FACTOR = 0.75f;
    Node[] buckets = new Node[DEFAULT_CAPACITY];
    private int size = 0;

    public void put(String key, String value) {
        int index = getIndex(key);
        var head = buckets[index];
        //Om head == null bucket är tom
        //Om head != null använd equals och följ next tills vi hittar en match eller null


    }

    private int getIndex(String key) {
        if (key == null)
            return 0;
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public String get(String key) {

    }

    class Node {
        String key;
        String value;
        Node next;
    }


    static void main() {
        HashMap
        String s1 = "Aa";
        String s2 = "BB";
        String s3 = "Cc";

        MyHashMap mh = new MyHashMap();
        mh.put(s1, "Value1");
        mh.put(s2, "Value2");
        mh.put(s3, "Value3");

        IO.println(s1.hashCode());
        IO.println(s2.hashCode());
        IO.println(s1.equals(s2));
        IO.println(s3.hashCode());
    }
}
