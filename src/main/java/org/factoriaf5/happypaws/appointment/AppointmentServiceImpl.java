package org.factoriaf5.happypaws.appointment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository repository;

    @Override
    public List<AppointmentEntity> getAllAppointments() {
        return repository.findAll();
    }

    @Override
    public List<AppointmentEntity> getAppointmentsByUser(Long idUser) {
        return repository.findByPatient_IdUser(idUser);
    }

    @Override
    public AppointmentEntity createAppointment(AppointmentEntity appointment) {
        // Validación máximo 10 citas por día
        LocalDate date = appointment.getDateTime().toLocalDate();
        long count = repository.findByDateTimeBetween(date.atStartOfDay(), date.plusDays(1).atStartOfDay()).size();
        if (count >= 10) {
            throw AppointmentException.MaxAppointmentsPerDay();
        }
        appointment.setStatus(AppointmentStatus.PENDIENTE);
        return repository.save(appointment);
    }

    @Override
    public AppointmentEntity updateAppointment(Long id, AppointmentEntity updatedAppointment) {
        AppointmentEntity appointment = repository.findById(id)
                .orElseThrow(AppointmentException::AppointmentNotFound);
        appointment.setDateTime(updatedAppointment.getDateTime());
        appointment.setPatient(updatedAppointment.getPatient());
        appointment.setType(updatedAppointment.getType());
        appointment.setReason(updatedAppointment.getReason());
        appointment.setStatus(updatedAppointment.getStatus());
        return repository.save(appointment);
    }

    @Override
    public void deleteAppointment(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void markPastAppointments() {
        List<AppointmentEntity> all = repository.findAll();
        LocalDateTime now = LocalDateTime.now();
        all.forEach(a -> {
            if (a.getStatus() == AppointmentStatus.PENDIENTE && a.getDateTime().isBefore(now)) {
                a.setStatus(AppointmentStatus.PASADA);
                repository.save(a);
            }
        });
    }
}