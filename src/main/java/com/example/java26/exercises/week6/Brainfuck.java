package com.example.java26.exercises.week6;

public class Brainfuck {
    public static int MEMORY_SIZE = 30000;
    int[] memory = new int[MEMORY_SIZE];
    int pointer = 0;

    static void main() {
        Brainfuck brainfuck = new Brainfuck();
        //Hello World
        brainfuck.execute(">++++++++[<+++++++++>-]<.>++++[<+++++++>-]<+.+++++++..+++.>>++++++[<+++++++>-]<++.------------.>++++++[<+++++++++>-]<+.<.+++.------.--------.>>>++++[<++++++++>-]<+.");
        IO.println("");
    }

    public void execute(String code) {
        int pc = 0;
        while (pc < code.length()) {
            char c = code.charAt(pc);
            switch (c) {
                case '+' -> memory[pointer] = (memory[pointer] + 1) & 0xFF;
                case '-' -> memory[pointer] = (memory[pointer] - 1) & 0xFF;
                case '>' -> pointer = (pointer == MEMORY_SIZE - 1) ? 0 : pointer + 1;
                case '<' -> pointer = (pointer == 0) ? MEMORY_SIZE - 1 : pointer - 1;
                case '.' -> System.out.print((char) memory[pointer]);
                case '[' -> {
                    if (memory[pointer] == 0) {
                        pc = jumpForward(pc, code);
                    }
                }
                case ']' -> {
                    if (memory[pointer] != 0) {
                        pc = jumpBackward(pc, code);
                    }
                }
            }
            pc++; // Gå till nästa instruktion
        }
    }

    int jumpForward(int pc, String code) {
        int depth = 0;
        while (pc < code.length()) {
            char c = code.charAt(pc);
            if (c == '[') depth++;
            if (c == ']') depth--;
            if (depth == 0) return pc; // Hittade matchande ]
            pc++;
        }
        return pc;
    }

    int jumpBackward(int pc, String code) {
        int depth = 0;
        while (pc >= 0) {
            char c = code.charAt(pc);
            if (c == ']') depth++;
            if (c == '[') depth--;
            if (depth == 0) return pc; // Hittade matchande [
            pc--;
        }
        return pc;
    }
}
