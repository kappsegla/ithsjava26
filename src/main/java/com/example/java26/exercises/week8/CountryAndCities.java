package com.example.java26.exercises.week8;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CountryAndCities {


    static void main() {
        //First and last element in stream
        getCountries().stream().findFirst().ifPresent(System.out::println);
        getCountries().stream().reduce((_, t2) -> t2).ifPresent(System.out::println);
        var country = getCountries().stream()
                .map(Country::countryName)
                .skip(getCountries().size() - 1L).findFirst().orElse("");
        IO.println(country);

        //Sorting increasing
        getCountries().stream()
                .sorted(Comparator.comparing(Country::countryName))
                .forEach(IO::println);
        getCountries().stream()
                .map(Country::countryName)
                .sorted()
                .forEach(IO::println);
        //Sorting decreasing population
        getCountries().stream()
                .sorted(Comparator.comparing(Country::population).reversed())
                .forEach(IO::println);

        //Max population
        IO.println(getCountries().stream()
                .max(Comparator.comparing(Country::population))
                .map(c -> String.format("Max population: %,.0f", c.population() * 1_000_000))
                .orElse(""));

        //Mean area and number of countries with less area
        double average = getCountries().stream()
                .mapToInt(Country::area)
                .average().orElse(0.0);
        IO.println(String.format("Average area: %,.2f", average));
        IO.println(getCountries().stream().filter(c -> c.area() < average).count());

        //Filter and joining
        IO.println(getCountries().stream()
                .filter(c -> c.population() < 5.0)
                .map(Country::countryName)
                .collect(Collectors.joining(" ")));
        //
        var greaterThan10000 = getCountries().stream()
                .filter(c -> c.area > 10000)
                .count();
        var greaterThan100000 = getCountries().stream()
                .filter(c -> c.area > 100_000)
                .count();
        var greaterThan1000000 = getCountries().stream()
                .filter(c -> c.area > 1_000_000)
                .count();
        IO.println(greaterThan10000 + " " + greaterThan100000 + " " + greaterThan1000000);
        {
            int a = 0;
            int b = 0;
            int c = 0;
            for (var ctry : getCountries()) {
                if (ctry.area > 1_000_000) {
                    a++;
                    b++;
                    c++;
                } else if (ctry.area > 100_000) {
                    a++;
                    b++;
                } else if (ctry.area > 10_000)
                    a++;
            }
            IO.println(a + " " + b + " " + c);
        }
        //Countries and capital starts with same letter
        getCountries().stream()
                .filter(c -> c.countryName().charAt(0) == c.capital().charAt(0))
                .map(c -> c.countryName() + " " + c.capital())
                .forEach(IO::println);
        //Countries with more letters in the name than in the capital
        getCountries().stream()
                .filter(c -> c.countryName().length() > c.capital().length())
                .map(Country::countryName)
                .forEach(IO::println);
        IO.println("=====");
        //5 smallest populations
        getCountries().stream()
                .sorted(Comparator.comparing(Country::population))
                .limit(5)
                .map(Country::countryName)
                .forEach(IO::println);
        IO.println("=====");
        //3 first countries with > 7 mil
        getCountries().stream()
                .filter(c -> c.population() > 7.0)
                .sorted(Comparator.comparing(Country::population))
                .limit(3)
                .map(Country::countryName)
                .forEach(IO::println);
        //Exercise 13
        getCountries().stream()
                .filter(c -> c.area() >= 500000)
                .sorted(Comparator.comparing(Country::area).reversed())
                .limit(3)
                .map(c -> c.countryName + "," + c.area())
                .forEach(IO::println);
        IO.println("=====");
        //Grouping by and counting
        var result = getCountries().stream()
                .collect(Collectors.groupingBy(c -> c.countryName().charAt(0), Collectors.counting()));
        result.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))  //Just to print everything in order
                .forEach(IO::println);

        IO.println("=====");


    }


    private static List<Country> getCountries() {
        return List.of(
                new Country("Sverige", "Stockholm", 10.07, 450295),
                new Country("Norge", "Oslo", 5.27, 323802),
                new Country("Island", "Reykjavik", 0.33, 102775),
                new Country("Danmark", "Köpenhamn", 5.75, 42931),
                new Country("Finland", "Helsinki", 5.51, 338424),
                new Country("Belgien", "Bryssel", 11.30, 30528),
                new Country("Tyskland", "Berlin", 82.18, 357168),
                new Country("Frankrike", "Paris", 66.99, 640679),
                new Country("Storbritannien", "London", 60.80, 209331),
                new Country("Niue", "Alofi", 0.0016, 261),
                new Country("Mongoliet", "Ulan Batar", 3.08, 1566000),
                new Country("Polen", "Warszawa", 38.63, 312679),
                new Country("Spanien", "Madrid", 46.5, 505990),
                new Country("Portugal", "Lissabon", 10.31, 92212),
                new Country("Italien", "Rom", 60.59, 301338),
                new Country("Grekland", "Aten", 11.18, 131957),
                new Country("Luxemburg", "Luxemburg", 0.58, 2586),
                new Country("Liechtenstein", "Vaduz", 0.038, 160));
    }


    record Country(String countryName, String capital, double population, int area) {
    }

}
