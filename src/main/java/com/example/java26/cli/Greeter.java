package com.example.java26.cli;

import java.time.Clock;
import java.time.LocalTime;

public class Greeter {
    private Clock clock;

    public Greeter(){
        this.clock = Clock.systemDefaultZone();
    }

    public Greeter(Clock clock){
        this.clock = clock;
    }

    public String greet(String name) {
        if( LocalTime.now(clock).isBefore( LocalTime.NOON))
            return "Good morning " + name;
        else if( LocalTime.now(clock).isBefore( LocalTime.of(18,0,0)))
            return "Good afternoon " + name;
        else
            return "Good evening " + name;
    }

    static void main() {
        Greeter greeter = new Greeter();
        IO.println(greeter.greet("Martin"));
    }
}
