package com.drones.errors;


public class DroneNotFoundException extends RuntimeException{
    public DroneNotFoundException(String message) {
        super(message);
    }
}
