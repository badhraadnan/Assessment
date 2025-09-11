package com.assessment.webApplicationBackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class customerExceptionAdvice {

    @ExceptionHandler(NotesNotFound.class)
    public ResponseEntity<String> handleCustomerNotFound(NotesNotFound customerNotFound){
        return new ResponseEntity<>(customerNotFound.getMessage(),HttpStatus.NOT_FOUND);
    }
}
