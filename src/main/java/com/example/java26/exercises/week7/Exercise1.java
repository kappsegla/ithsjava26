package com.example.java26.exercises.week7;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Exercise1 {
    static void main() {

        List<String> list = new ArrayList<>();
        list.add("ad");
        list.add(0, "ba");
        list.addFirst("ce");
        list.addLast("db");

        IO.println(list.get(1));
        IO.println(list.getFirst());
        IO.println(list.getLast());

        var replacedItem = list.set(0, "new");
        IO.println(replacedItem);
        IO.println(list.getFirst());

//        list.remove("new");
//        list.remove(1);
//        list.removeFirst();
//        list.removeLast();
        IO.println(list.isEmpty());
        IO.println(list.size());
        list.add("new");
        IO.println(list.contains(new String("new")));

        list.sort(new StringSorter());
        list.forEach(IO::println);
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Mike", 13));
        persons.add(new Person("Kalle", 10));
        persons.add(new Person("Laura", 15));
        persons.add(new Person("Olle", 12));
        persons.add(new Person("Laura", 14));
        persons.sort(Comparator
                .comparing(Person::name)
                .reversed()
                .thenComparing(Person::age));
        persons.forEach(IO::println);
    }
}

class StringSorter implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return o1.charAt(o1.length() - 1) - o2.charAt(o2.length() - 1);
    }
}

record Person(String name, int age) {
}
