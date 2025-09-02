package com.drones.errors;


public class InvalidInputParameterException extends RuntimeException{
    public InvalidInputParameterException(String message) {
        super(message);
    }
}
