package org.factoriaf5.happypaws.appointment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppointmentExceptionHandler {

    @ExceptionHandler(AppointmentException.class)
    public ResponseEntity<String> handleAppointmentException(AppointmentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}