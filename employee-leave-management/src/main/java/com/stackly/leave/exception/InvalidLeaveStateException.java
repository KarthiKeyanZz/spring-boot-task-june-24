package com.stackly.leave.exception;

public class InvalidLeaveStateException extends RuntimeException {

    public InvalidLeaveStateException(String message) {
        super(message);
    }
}
