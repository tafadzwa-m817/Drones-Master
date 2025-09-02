package com.drones.errors;


public class MedicationNotFoundException extends RuntimeException{
    public MedicationNotFoundException(String message) {
        super(message);
    }
}
