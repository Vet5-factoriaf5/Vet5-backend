package org.factoriaf5.happypaws.appointment;

import java.util.List;

public interface AppointmentService {

    List<AppointmentEntity> getAllAppointments();

    List<AppointmentEntity> getAppointmentsByUser(Long idUser);

    AppointmentEntity createAppointment(AppointmentEntity appointment);

    AppointmentEntity updateAppointment(Long id, AppointmentEntity appointment);

    void deleteAppointment(Long id);

    void markPastAppointments();
}