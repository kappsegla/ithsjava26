package com.example.java26.generics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyHashMapTest {

    MyHashMap map = new MyHashMap();

    @Test
    void createdMapHasSizeZero() {
        assertEquals(0, map.size());
    }

    @Test
    void afterAddingOneKeySizeShouldBeOne() {
        map.put("1", "1");
        assertEquals(1, map.size());
    }

    @Test
    void addingKeysAndUsingGet() {
        map.put("1", "1");
        map.put("2", "2");
        assertEquals("1", map.get("1"));
        assertEquals("2", map.get("2"));
        assertEquals(2, map.size());
    }

    @Test
    void addingSameKeyAndUsingGet() {
        map.put("1", "1");
        map.put("1", "2");
        assertEquals("2", map.get("1"));
        assertEquals(1, map.size());
    }

    @Test
    void addingNullAsKey() {
        map.put(null,"Value");
        assertEquals("Value", map.get(null));
        assertEquals(1, map.size());
    }

    @Test
    void usingNullAsKeyMultipleTimes() {
        map.put(null,"Value");
        map.put(null,"Value");
        assertEquals("Value", map.get(null));
        assertEquals(1, map.size());
    }

    @Test
    void puttingValuesInSameBucket() {
        map.put("Aa", "1");
        map.put("BB", "2");
        map.put("Cc", "3");
        map.put("null", "0");
        assertEquals("1", map.get("Aa"));
        assertEquals("2", map.get("BB"));
        assertEquals("3", map.get("Cc"));
        assertEquals("0", map.get("null"));
        assertEquals(4, map.size());
    }

    @Test
    void getNotExistingKey() {
        map.put("Aa", "1");
        map.put("BB", "2");
        map.put("Cc", "3");
        assertEquals(null, map.get("Kk"));
    }
}
