package org.factoriaf5.happypaws.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {

    // Buscar todas las citas en un rango de tiempo
    List<AppointmentEntity> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);

    // Buscar todas las citas de un paciente concreto
    List<AppointmentEntity> findByPatient_Id(Long patientId);
}