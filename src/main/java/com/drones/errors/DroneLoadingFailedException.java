package com.drones.errors;


public class DroneLoadingFailedException extends RuntimeException{
    public DroneLoadingFailedException(String message) {
        super(message);
    }
}
