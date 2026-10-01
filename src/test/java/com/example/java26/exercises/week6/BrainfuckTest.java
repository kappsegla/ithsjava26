package com.example.java26.exercises.week6;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class BrainfuckTest {

    Brainfuck brainfuck = new Brainfuck();
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream capturedOut;

    @BeforeEach
    void setUp() {
        // Intercept System.out to capture console output printed by the application
        capturedOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capturedOut, true, StandardCharsets.UTF_8));
    }

    @AfterEach
    void tearDown() {
        // ALWAYS restore original system streams to avoid polluting other tests
        System.setIn(originalIn);
        System.setOut(originalOut);
    }


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

    @Test
    void outputASCIICharacterAtPointer() {
        brainfuck.memory[0] = 65;
        brainfuck.execute(".");
        String consoleOutput = capturedOut.toString(StandardCharsets.UTF_8);
        assertEquals("A", consoleOutput);
    }

    @Test
    void loopSkipsWhenZero() {
        brainfuck.memory[brainfuck.pointer] = 0;

        brainfuck.execute("[+]");

        assertEquals(0, brainfuck.memory[0],
                "Loopen ska hoppas över när cellen är 0");
    }

    @Test
    void loopExecutesWhenNonZero() {
        brainfuck.memory[0] = 1;

        brainfuck.execute("[->+<]");

        assertEquals(0, brainfuck.memory[0],
                "Loopen ska köras tills cellen blir 0");
    }

    @Test
    void loopBackwardsJumpWhenNonZero() {
        brainfuck.execute("+[-]");

        assertEquals(0, brainfuck.memory[0],
                "] ska hoppa bakåt när cellen är ≠ 0, men inte när den är 0");
    }

    @Test
    void loopRepeatsUntilZero() {
        brainfuck.execute("+++[-]");

        assertEquals(0, brainfuck.memory[0],
                "Loopen ska repetera tills cellen blir 0");
    }

    @Test
    void nestedLoopsExecuteCorrectly() {
        // Program:
        // ++         cell0 = 2
        // [          outer loop (runs twice)
        //   >+       cell1 += 1
        //   [        inner loop (runs until cell1 == 0)
        //     >++    cell2 += 2
        //     <-     cell1 -= 1
        //   ]        end inner loop
        //   <-       cell0 -= 1
        // ]          end outer loop

        brainfuck.execute("++[>+[>++<-]<-]");

        assertEquals(0, brainfuck.memory[0], "Cell 0 ska vara 0 efter yttre loop");
        assertEquals(0, brainfuck.memory[1], "Cell 1 ska vara 0 efter inre loop");
        assertEquals(4, brainfuck.memory[2], "Cell 2 ska ha ackumulerat värdet 4");
    }
}
