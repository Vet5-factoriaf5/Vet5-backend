package org.factoriaf5.happypaws.appointment;

import java.util.List;


public interface AppointmentService {

    AppointmentDTOResponse create(AppointmentDTORequest dto);

    AppointmentDTOResponse update(Long id, AppointmentDTORequest dto);

    void delete(Long id);

    AppointmentDTOResponse getById(Long id);

    List<AppointmentDTOResponse> getAll();

    List<AppointmentDTOResponse> getByPatient(Long patientId);
}