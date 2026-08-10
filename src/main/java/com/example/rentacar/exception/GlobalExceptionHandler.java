package com.example.rentacar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> handleUserExist(UserAlreadyExistsException userAlreadyExistsException){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", userAlreadyExistsException.getMessage()));
    }
}
