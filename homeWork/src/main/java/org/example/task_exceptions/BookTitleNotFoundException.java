package org.example.task_exceptions;

public class BookTitleNotFoundException extends Exception {

    public BookTitleNotFoundException() {}

    public BookTitleNotFoundException(String message) {
        super(message);
    }

    public BookTitleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
