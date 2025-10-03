package org.factoriaf5.happypaws.appointment;

import lombok.RequiredArgsConstructor;
import org.factoriaf5.happypaws.patient.PatientEntity;
import org.factoriaf5.happypaws.patient.PatientRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppointmentMapper {

    private final PatientRepository patientRepository;

    // Convierte DTO a entidad JPA
    public AppointmentEntity toEntity(AppointmentDTORequest dto) {
        PatientEntity patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Paciente con id " + dto.getPatientId() + " no encontrado"));

        return AppointmentEntity.builder()
                .dateTime(dto.getDateTime())
                .reason(dto.getReason())
                .type(dto.getType())
                .status(AppointmentStatus.PENDING) // al crear siempre empieza como PENDIENTE
                .patient(patient)
                .build();
    }

    // Convierte entidad JPA a DTO
    public AppointmentDTOResponse toDTO(AppointmentEntity entity) {
        return AppointmentDTOResponse.builder()
                .id(entity.getId())
                .patientId(entity.getPatient().getId()) // devolvemos solo el id del paciente
                .dateTime(entity.getDateTime())
                .reason(entity.getReason())
                .type(entity.getType())
                .status(entity.getStatus())
                .build();
    }
}