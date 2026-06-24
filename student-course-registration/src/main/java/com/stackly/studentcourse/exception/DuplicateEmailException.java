package com.stackly.studentcourse.exception;

public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException(String email) {
        super("A student is already registered with email: " + email);
    }
}
