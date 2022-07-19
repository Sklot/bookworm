package ru.itfb.bookworm;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException() {
        super("Could not find book");
    }
}
