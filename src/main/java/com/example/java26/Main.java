package com.example.java26;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        IO.println("Hello World!");

        int tal = 10;
        long hugeNumber = 10000000003300L;
        double length = 1.85f;
        var weight = 75.0;

        float fValue = 16777216.0f;
        BigDecimal bigValue = new BigDecimal("16777217.0");
        IO.println(bigValue.add(BigDecimal.ONE));

        int money = 10050;
        IO.println(money / 100.0);

        System.out.printf("%.20f", length);
        IO.println(tal);
        IO.println(tal + 1);
        tal = tal + 1;
        IO.println(tal);

        int binary = 0b0101;
        IO.println(binary);

        int initialize;
        initialize = 1 + 2;
        int k = initialize + 1;
        IO.println(initialize);
    }
}
