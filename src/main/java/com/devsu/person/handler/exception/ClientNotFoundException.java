package com.devsu.person.handler.exception;

public class ClientNotFoundException extends IllegalArgumentException {

    public ClientNotFoundException(String message) {
        super(message);
    }
}
