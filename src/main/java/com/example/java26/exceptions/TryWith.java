package com.example.java26.exceptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TryWith {
    static void main() {

        try (ClosableDemo closable = new ClosableDemo();
             Scanner scanner = new Scanner(new File("test.txt"));
        ) {
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}

class ClosableDemo implements AutoCloseable {
    @Override
    public void close() throws RuntimeException {
        System.out.println("Closing...");
    }
}
