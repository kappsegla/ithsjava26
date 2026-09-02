package com.example.java26;

public class Logic {
    static void main() {
        int value = -1;
        if (greaterThanZero(value) && lessThanTen(value))
            IO.println("Sant");
    }

    static boolean greaterThanZero(int v) {
        if (v > 0)
            return true;
        return false;
    }

    static boolean lessThanTen(int v) {
        return v < 10;
    }
}
/*
a      b     &&         ||       ^ (xor) circumflex
false false  false      false    false
true  false  false      true     true
true  true   true       true     false
false true   false      true     true
 */
