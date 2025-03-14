package com.devsu.person.handler.exception;

public class DuplicateIdentificationException extends IllegalArgumentException {

    public DuplicateIdentificationException(String message) {
        super(message);
    }
}
