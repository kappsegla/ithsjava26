package com.example.java26.cli;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    @Test
    void whenAddingTwoAndTowShouldBecomeFour() {
        var result = Calculator.add(2, 2);

        assertEquals(4, result);
    }

    @Test
    @DisplayName("abc should count as 3")
    void whenCharacterCountIsCalledWithABCShouldReturnThree() {
        //Arrange, Given
        String input = "abc";
        //Act, When
        var result = Calculator.graphemeCount(input);
        //Assert, Then
        assertEquals(3, result);
    }

    @Test
    @DisplayName("😀 should count as 1")
    void whenCharacterCountIsCalledWithSpecialSymbolShouldReturnOne() {
        var result = Calculator.graphemeCount("😀");
        assertEquals(1, result);
    }

    @Test
    @DisplayName("👍🏿 should count as 1")
    void whenCharacterCountIsCalledWithSpecialSymbol2ShouldReturnOne() {
        var result = Calculator.graphemeCount("👍🏿");
        assertEquals(1, result);
    }

    @Test
    @DisplayName("👨🏻‍👩🏽‍👧🏾‍👦🏻 should count as 1")
    void whenCharacterCountIsCalledWithSpecialSymbolFamilyShouldReturnOne() {
        var result = Calculator.graphemeCount("👨🏻‍👩🏽‍👧🏾‍👦🏻");
        assertEquals(1, result);
    }

}
