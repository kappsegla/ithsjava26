package com.example.java26.exercises.week6;

public class Brainfuck {
    public static int MEMORY_SIZE = 30000;
    char[] memory = new char[MEMORY_SIZE];
    int pointer = 0;

    static void main() {


    }

    public void execute(String code) {
        for (Character c : code.toCharArray()) {
            if (c == '+') {
                if (memory[pointer] == 255) {
                    memory[pointer] = 0;
                } else {
                    memory[pointer]++;
                }
            } else if (c == '-') {
                if (memory[pointer] == 0) {
                    memory[pointer] = 255;
                } else {
                    memory[pointer]--;
                }
            } else if (c == '>') {
                if (pointer == MEMORY_SIZE - 1)
                    pointer = 0;
                else
                    pointer++;
            } else if (c == '<') {
                if (pointer == 0)
                    pointer = MEMORY_SIZE - 1;
                else
                    pointer--;
            }
        }

    }
}
