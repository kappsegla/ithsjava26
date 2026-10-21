package com.example.java26.exceptions;

import java.io.IOException;

public class ExceptionsDemo {

    static void main() throws IOException {
        try {
            divide(1, 0);
        } catch (Exception e) {
            switch (e) {
                case ArithmeticException _ -> System.out.println("ArithmeticException");
                default -> System.out.println("Unexpected exception: " + e.getMessage());
            }
        } finally {
            //Do cleanup
            System.out.println("Finally");
        }

        Storage storage = new Storage(null);

//        Path path = Path.of("C:", "users", "marti", "nofile");
//        String content = Files.readString(path);
    }

    public static int divide(int a, int b) {
        return a / b;
    }

    static class Storage {
        private final String text;

        Storage(String text) {
            if (text == null)
                throw new StorageException("Text cannot be null", text);
            this.text = text;
        }
    }
}
class StorageException extends RuntimeException {
    private final String text;
    public StorageException(String message, String text) {
        super(message);
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
