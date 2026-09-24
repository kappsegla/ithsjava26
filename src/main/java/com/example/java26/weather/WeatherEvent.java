package com.example.java26.weather;

import com.example.java26.weather.WeatherEvent.*;

public sealed interface WeatherEvent  {
    record Rain(int mmPerHour) implements WeatherEvent {    }
    record Wind(int metersPerSecond) implements WeatherEvent {    }
    record Sun(int uvIndex) implements WeatherEvent {    }
}


class WeatherUtils {

    public static String riskLevel(WeatherEvent event) {
        return switch (event) {
            case Wind w -> w.metersPerSecond() > 15 ? "High" : "Low";
            case Rain r -> r.mmPerHour() > 20 ? "High" : "Low";
            case Sun s -> s.uvIndex() > 7 ? "High" : "Low";
        };
    }

    public static String label(WeatherEvent event) {
        return switch (event) {
            case Wind w -> "Wind is " + w.metersPerSecond();
            case Rain r -> "Rain falls with " + r.mmPerHour();
            case Sun s -> "Sun uvIndex is " + s.uvIndex();
        };
    }
}
