package com.drones.errors;


public class AboveMaxWeightLimitException extends RuntimeException {
    public AboveMaxWeightLimitException(String message) {
        super(message);
    }
}
