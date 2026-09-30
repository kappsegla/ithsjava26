package com.example.java26.oop;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MultipleIntegersTest {

    MultipleIntegers newInstance = new MultipleIntegers();

    @Test
    void removingOneValueShouldDecreaseSize() {
        newInstance.add(1);
        newInstance.removeLast();
        assertEquals(0, newInstance.size());
    }

    @Test
    void removingValueFromEmptyListShouldNotThrowException() {
        assertDoesNotThrow(newInstance::removeLast);
        assertEquals(0, newInstance.size());
        assertThat(newInstance.size()).isEqualTo(0);  //AssertJ fluent Api
    }

    @Test
    void removingLastValueShouldUpdateValuesCorrectly() {
        newInstance.add(1);
        newInstance.add(2);
        newInstance.add(3);

        newInstance.removeLast();

        assertEquals(2, newInstance.size());
        assertEquals(1, newInstance.getValue(0));
        assertEquals(2, newInstance.getValue(1));
    }

    @Test
    void newInstanceShouldBeEmpty() {
        assertEquals(0, newInstance.size());
    }

    @Test
    void addingOneValueShouldMakeSizeOne() {
        newInstance.add(1);
        assertEquals(1, newInstance.size());
    }

    @Test
    void afterAddingOneValueIndex0ShouldReturnValue() {
        newInstance.add(1);
        assertEquals(1, newInstance.getValue(0));
    }

    @Test
    void addingMoreThanTenValuesWorks() {
        for (int i = 0; i < 11; i++) {
            newInstance.add(i);
        }
        assertEquals(11, newInstance.size());
        assertEquals(0, newInstance.getValue(0));
        assertEquals(10, newInstance.getValue(10));
    }

    @Test
    void addingValueFirstShould() {
        newInstance.addFirst(1);
        newInstance.addFirst(2);
        assertEquals(2, newInstance.getValue(0));
        assertEquals(1, newInstance.getValue(1));
    }


}
