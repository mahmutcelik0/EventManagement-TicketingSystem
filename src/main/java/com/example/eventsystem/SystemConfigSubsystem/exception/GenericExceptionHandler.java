package com.example.eventsystem.SystemConfigSubsystem.exception;

import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GenericExceptionHandler {
    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<ErrorMessage> runtimeExceptionHandler(RuntimeException ex) {
        return new ResponseEntity<>(new ErrorMessage("Error in project"), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> notFoundExceptionHandler(NotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND); // {message:"..."} lı şekle dönüştürülebilir
    }
}
