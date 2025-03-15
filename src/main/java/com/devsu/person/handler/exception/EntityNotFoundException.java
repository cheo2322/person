package com.devsu.person.handler.exception;

public class EntityNotFoundException extends IllegalArgumentException {

    public EntityNotFoundException(String message) {
        super(message);
    }
}
