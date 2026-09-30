package com.example.java26.cli;

public class StringParser {
    public int parseAndAdd(String s) {
        if( s == null || s.isEmpty() ) return 0;
        if( s.contains(",") ){
            var values = s.split(",");
            int sum = 0;
            for(String str : values){
                sum += Integer.parseInt(str);
            }
            return sum;
        }
        return Integer.parseInt(s);
    }
}
