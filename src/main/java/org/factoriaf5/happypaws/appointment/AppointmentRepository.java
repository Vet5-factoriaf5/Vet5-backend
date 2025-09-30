package org.factoriaf5.happypaws.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {

    List<AppointmentEntity> findByPatient_IdUser(Long idUser); // citas de un cliente específico

    List<AppointmentEntity> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);
}