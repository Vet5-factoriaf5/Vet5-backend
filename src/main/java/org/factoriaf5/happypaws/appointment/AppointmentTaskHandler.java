package org.factoriaf5.happypaws.appointment;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Maneja tareas automáticas sobre las citas:
 * - Marca como PASADA las citas pendientes ya vencidas.
 * - Elimina citas PASADA con más de 3 meses.
 */
@EnableScheduling
@Component
@RequiredArgsConstructor
public class AppointmentTaskHandler {

    private final AppointmentRepository repository;

    // Se ejecuta cada noche a las 3 AM
    @Scheduled(cron = "0 0 3 * * *")
    public void updateAppointments() {
        LocalDateTime now = LocalDateTime.now();

        repository.findAll().forEach(appointment -> {
            if (appointment.getStatus() == AppointmentStatus.PENDING &&
                appointment.getDateTime().isBefore(now)) {
                appointment.setStatus(AppointmentStatus.MISSED);
                repository.save(appointment);
            }

            if (appointment.getStatus() == AppointmentStatus.MISSED &&
                appointment.getDateTime().isBefore(now.minusMonths(3))) {
                repository.delete(appointment);
            }
        });
    }
}