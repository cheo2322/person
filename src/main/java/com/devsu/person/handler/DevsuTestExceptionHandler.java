package com.devsu.person.handler;

import com.devsu.person.handler.exception.DuplicateEntityException;
import com.devsu.person.handler.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DevsuTestExceptionHandler {

  @ExceptionHandler(DuplicateEntityException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<DevsuError> handleDuplicateIdentificationException(
      DuplicateEntityException ex) {
    return new ResponseEntity<>(new DevsuError(ex.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<DevsuError> handleClientNotFoundException(EntityNotFoundException ex) {
    return new ResponseEntity<>(new DevsuError(ex.getMessage()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<DevsuError> handleUnexpectedException(Exception ex) {
    return new ResponseEntity<>(
        new DevsuError("An unexpected error occurred. Please try again later."),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
