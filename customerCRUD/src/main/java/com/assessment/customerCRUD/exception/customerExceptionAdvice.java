package com.assessment.customerCRUD.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class customerExceptionAdvice {

    @ExceptionHandler(CustomerNotFound.class)
    public ResponseEntity<String> handleCustomerNotFound(CustomerNotFound customerNotFound){
        return new ResponseEntity<>(customerNotFound.getMessage(),HttpStatus.NOT_FOUND);
    }
}
