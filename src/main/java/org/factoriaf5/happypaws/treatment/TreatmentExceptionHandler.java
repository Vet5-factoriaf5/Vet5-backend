package org.factoriaf5.happypaws.treatment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class TreatmentExceptionHandler {

    @ExceptionHandler(TreatmentException.class)
    public ResponseEntity<String> handleTreatmentException(TreatmentException ex) {
        String message = ex.getMessage().toLowerCase().contains("no encontrado")
                ? "Error: " + ex.getMessage()
                : "Validación: " + ex.getMessage();
        HttpStatus status = ex.getMessage().toLowerCase().contains("no encontrado")
                ? HttpStatus.NOT_FOUND
                : HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(message);
    }
}