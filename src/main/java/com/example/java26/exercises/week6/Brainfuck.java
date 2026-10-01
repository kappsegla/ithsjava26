package com.example.java26.exercises.week6;

public class Brainfuck {
    public static int MEMORY_SIZE = 30000;
    char[] memory = new char[MEMORY_SIZE];
    int pointer = 0;

    static void main() {
        Brainfuck brainfuck = new Brainfuck();
        //Hello World
        brainfuck.execute("+[>[<->+]<-]>>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.");

    }

    public void execute(String code) {
        for (int pc = 0; pc < code.length(); pc++) {
            char c = code.charAt(pc);
            switch (c) {
                case '+' -> memory[pointer] = (char) (memory[pointer] == 255 ? 0 : memory[pointer] + 1);
                case '-' -> memory[pointer] = (char) (memory[pointer] == 0 ? 255 : memory[pointer] - 1);
                case '>' -> pointer = (pointer == MEMORY_SIZE - 1) ? 0 : pointer + 1;
                case '<' -> pointer = (pointer == 0) ? MEMORY_SIZE - 1 : pointer - 1;
                case '.'-> IO.print(memory[pointer]);
                case '[' -> {
                    if (memory[pointer] == 0) {
                        pc = jumpForward(pc, code); // hitta matchande ]
                    }
                }
            }
        }
    }

    int jumpForward(int pc, String code) {
        int depth = 1;
        while (depth > 0) {
            pc++;
            if (code.charAt(pc) == '[') depth++;
            else if (code.charAt(pc) == ']') depth--;
        }
        return pc;
    }

}
