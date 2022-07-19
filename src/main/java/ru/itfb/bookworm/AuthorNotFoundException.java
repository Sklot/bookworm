package ru.itfb.bookworm;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException() {
        super("Could not find author");
    }
}
