package com.example.rentacar.exception;

public class NumberAlreadyExistException extends RuntimeException {
    public NumberAlreadyExistException(String message) {
        super(message);
    }
}
