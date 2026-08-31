package com.example.rentacar.exception;

public class CarIsNotAvailableException extends RuntimeException {
    public CarIsNotAvailableException(String message) {
        super(message);
    }
}
