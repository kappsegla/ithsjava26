package com.example.java26.exercises.week8;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

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
