package com.devsu.person.handler;

import com.devsu.person.handler.exception.ClientNotFoundException;
import com.devsu.person.handler.exception.DuplicateIdentificationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DevsuTestExceptionHandler {

  @ExceptionHandler(DuplicateIdentificationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<DevsuError> handleDuplicateIdentificationException(
      DuplicateIdentificationException ex) {
    return new ResponseEntity<>(new DevsuError(ex.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ClientNotFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<DevsuError> handleClientNotFoundException(ClientNotFoundException ex) {
    return new ResponseEntity<>(new DevsuError(ex.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<DevsuError> handleUnexpectedException(Exception ex) {
    return new ResponseEntity<>(
        new DevsuError("An unexpected error occurred. Please try again later."),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
