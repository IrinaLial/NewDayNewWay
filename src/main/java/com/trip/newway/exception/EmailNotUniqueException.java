package com.trip.newway.exception;

public class EmailNotUniqueException extends RuntimeException {

    public EmailNotUniqueException(String message) {
        super(message);
    }
}
