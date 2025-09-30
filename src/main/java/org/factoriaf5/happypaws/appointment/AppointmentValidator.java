package org.factoriaf5.happypaws.appointment;

import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class AppointmentValidator {

    public void validate(AppointmentEntity appointment) {
        if (appointment.getDateTime() == null || appointment.getDateTime().isBefore(LocalDateTime.now())) {
            throw AppointmentException.InvalidAppointmentDate();
        }
        if (appointment.getPatient() == null) {
            throw new AppointmentException("Paciente es obligatorio");
        }
        if (appointment.getReason() == null || appointment.getReason().isBlank()) {
            throw new AppointmentException("Motivo de la cita es obligatorio");
        }
    }
}