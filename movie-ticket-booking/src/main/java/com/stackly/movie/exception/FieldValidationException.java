package com.stackly.movie.exception;

public class FieldValidationException extends RuntimeException {

    public FieldValidationException(String message) {
        super(message);
    }
}
