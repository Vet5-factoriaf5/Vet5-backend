package org.factoriaf5.happypaws.appointment;

import java.util.List;


public interface AppointmentService {

    AppointmentResponseDTO create(AppointmentRequestDTO dto);

    AppointmentResponseDTO update(Long id, AppointmentRequestDTO dto);

    void delete(Long id);

    AppointmentResponseDTO getById(Long id);

    List<AppointmentResponseDTO> getAll();

    List<AppointmentResponseDTO> getByPatient(Long patientId);
}