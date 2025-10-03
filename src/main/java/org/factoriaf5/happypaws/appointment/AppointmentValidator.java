package org.factoriaf5.happypaws.appointment;

import java.time.LocalDateTime;


public class AppointmentValidator {

    public static void validate(AppointmentEntity appointment) {
        if (appointment.getDateTime() == null) {
            throw new AppointmentException("La fecha no puede estar vacía");
        }
        if (appointment.getDateTime().isBefore(LocalDateTime.now())) {
            throw new AppointmentException("La fecha debe ser futura");
        }
        if (appointment.getReason() == null || appointment.getReason().isBlank()) {
            throw new AppointmentException("El motivo es obligatorio");
        }
    }
}