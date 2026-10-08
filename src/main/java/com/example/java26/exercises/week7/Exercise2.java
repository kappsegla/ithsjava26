package com.example.java26.exercises.week7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Exercise2 {

    public static List<String> toList(String[] strings){
        List<String> list = new ArrayList<>();  //Modifiable arraylist with its own array
        for(String s : strings){
            list.add(s);
        }
        return list;
    }

    public static List<String> toList2(String[] strings){
        List<String> list = List.of(strings);  //Unmodifiable list
        return list;
    }

    public static List<String> toList3(String[] strings){
        List<String> list = Arrays.asList(strings);  //Arrays.asList uses strings array. Fixed size
        return list;
    }

    static void main() {



    }
}
