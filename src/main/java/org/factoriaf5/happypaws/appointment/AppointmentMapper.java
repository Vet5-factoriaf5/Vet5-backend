package org.factoriaf5.happypaws.appointment;

// TODO: Ruta temporal, cambiar por la entidad real cuando esté lista
import org.factoriaf5.happypaws.temp.Patient;
import org.springframework.stereotype.Component;

// Mapper encargado de convertir entre DTOs y Entidad JPA
// NOTA: ahora mismo usamos un Patient temporal con sólo el ID hasta que yely suba el patient real
@Component
public class AppointmentMapper {

    // Convierte DTO a entidad JPA
    public AppointmentEntity toEntity(AppointmentRequestDTO dto) {
        Patient patient = new Patient();
        patient.setId(dto.getPatientId()); 
        // TODO: cuando tengamos PatientEntity real → usar patientRepository.findById(dto.getPatientId())

        return AppointmentEntity.builder()
                .dateTime(dto.getDateTime())
                .reason(dto.getReason())
                .type(dto.getType())
                .status(AppointmentStatus.PENDING) // al crear siempre empieza como PENDIENTE
                .patient(patient)
                .build();
    }

    // Convierte entidad JPA a DTO
    public AppointmentResponseDTO toDTO(AppointmentEntity entity) {
        return AppointmentResponseDTO.builder()
                .id(entity.getId())
                .patientId(entity.getPatient().getId()) // devolvemos solo el id del paciente
                .dateTime(entity.getDateTime())
                .reason(entity.getReason())
                .type(entity.getType())
                .status(entity.getStatus())
                .build();
    }
}