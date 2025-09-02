package com.drones.api;

import com.drones.errors.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;


@RestControllerAdvice
public class GlobalRestControllerExceptionHandler {
    @ExceptionHandler(DroneNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<String> droneNotFoundException(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }
    @ExceptionHandler(MedicationNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<String> medicationNotFoundException(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }
    @ExceptionHandler(InvalidInputParameterException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<String> invalidInputParameter(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), BAD_REQUEST);
    }
    @ExceptionHandler(DroneLoadingFailedException.class)
    @ResponseStatus(OK)
    public ResponseEntity<String> droneLoadingFailedException(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), OK);
    }
    @ExceptionHandler(AuditEventLogNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<String> auditEventLogNotFoundException(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }

}
