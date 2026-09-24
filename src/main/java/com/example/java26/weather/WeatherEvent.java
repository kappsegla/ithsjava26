package com.example.java26.weather;

import com.example.java26.weather.WeatherEvent.Rain;
import com.example.java26.weather.WeatherEvent.Sun;
import com.example.java26.weather.WeatherEvent.Wind;

public sealed interface WeatherEvent {
    record Rain(int mmPerHour) implements WeatherEvent {
    }

    record Wind(int metersPerSecond) implements WeatherEvent {
    }

    record Sun(int uvIndex, float angle) implements WeatherEvent {
    }
}

class WeatherUtils {

    public static String riskLevel(WeatherEvent event) {
        return switch (event) {
            case Wind(int metersPerSecond) -> metersPerSecond > 15 ? "High" : "Low";
            case Rain(int mmPerHour) -> mmPerHour > 20 ? "High" : "Low";
            case Sun(int uvIndex, _) when uvIndex > 7 -> "High";
            case Sun(_, _) -> "Low";
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
