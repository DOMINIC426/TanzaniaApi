package com.java.wonderrelationship.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import java.time.LocalDateTime;
import java.util.concurrent.TimeoutException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> entity(ResourceNotFoundException resourceNotFoundException){
        ErrorResponse error=new ErrorResponse(
         resourceNotFoundException.getMessage(),HttpStatus.NOT_FOUND.value(), LocalDateTime.now().toString()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(TimeoutException.class)
    public ResponseEntity<ErrorResponse> errors(TimeoutException exception){
        ErrorResponse errorResponse=new ErrorResponse(
               exception.getMessage() ,HttpStatus.REQUEST_TIMEOUT.value(), LocalDateTime.now().toString()
        );
        return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(errorResponse);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> responseEntity(DataIntegrityViolationException dataIntegrityViolationException){
        ErrorResponse errorResponse = new ErrorResponse(
                dataIntegrityViolationException.getMessage(),
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now().toString()
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> general(Exception e){
        ErrorResponse errorResponse=new ErrorResponse(
                e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value(),LocalDateTime.now().toString()
        );

        return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(errorResponse);
    }


}
