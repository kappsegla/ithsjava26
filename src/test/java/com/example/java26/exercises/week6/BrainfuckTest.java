package com.example.java26.exercises.week6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrainfuckTest {

    Brainfuck brainfuck = new Brainfuck();

    @Test
    void incrementAtPointer() {
        brainfuck.execute("+");
        assertEquals(1, brainfuck.memory[0]);
    }

    @Test
    void decrementAtPointer() {
        brainfuck.execute("-");
        assertEquals(255, brainfuck.memory[0]);
    }

    @Test
    void decrementThenIncrement() {
        brainfuck.execute("-");
        brainfuck.execute("+");
        assertEquals(0, brainfuck.memory[0]);
    }

    @Test
    void incrementDataPointer() {
        brainfuck.execute(">");
        assertEquals(1, brainfuck.pointer);
    }

    @Test
    void decrementDataPointer() {
        brainfuck.execute("<");
        assertEquals(Brainfuck.MEMORY_SIZE - 1, brainfuck.pointer);
    }

    @Test
    void decrementThenIncrementDataPointer() {
        brainfuck.execute("<");
        brainfuck.execute(">");
        assertEquals(0, brainfuck.pointer);
    }

    @Test
    void combinedCommands() {
        // Startläge: pointer = 0, memory[0] = 0
        brainfuck.execute("+-><>");

        assertEquals(0, brainfuck.memory[0], "Cell 0 ska vara tillbaka på 0");
        assertEquals(1, brainfuck.pointer, "Pekaren ska ha flyttats till cell 1");
    }



}
