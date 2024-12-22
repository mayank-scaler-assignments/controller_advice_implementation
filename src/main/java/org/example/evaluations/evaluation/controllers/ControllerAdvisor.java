package org.example.evaluations.evaluation.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ControllerAdvisor {
    //Add Exception handlers here

    @ExceptionHandler(value = {IllegalArgumentException.class, NullPointerException.class})
    public ResponseEntity<Object> handleException(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(value = {IndexOutOfBoundsException.class})
    public ResponseEntity<Object> handleIndexOutOfBoundsException(Exception ex) {
        return ResponseEntity.internalServerError().body("Something went bad at our side");
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class, NumberFormatException.class})
    public ResponseEntity<Object> handlResponseEntity(Exception ex) {
        return new ResponseEntity<>("Please pass userId or cartId in correct format", HttpStatus.CONFLICT);
    }
}
