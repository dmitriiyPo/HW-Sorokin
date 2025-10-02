package org.example.task_exceptions;

public class NoCopyBookException extends Exception{

    public NoCopyBookException() {}

    public NoCopyBookException(String message) {
        super(message);
    }

    public NoCopyBookException(String message, Throwable cause) {
        super(message, cause);
    }
}
