package org.factoriaf5.happypaws.appointment;

import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    public AppointmentEntity toEntity(AppointmentRequestDTO dto) {
        return AppointmentEntity.builder()
                .dateTime(dto.getDateTime())
                .reason(dto.getReason())
                .type(dto.getType())
                .status(dto.getStatus())
                .patient(dto.getPatient())
                .build();
    }

    public AppointmentResponseDTO toDTO(AppointmentEntity entity) {
        return AppointmentResponseDTO.builder()
                .id(entity.getId())
                .dateTime(entity.getDateTime())
                .reason(entity.getReason())
                .type(entity.getType())
                .status(entity.getStatus())
                .patient(entity.getPatient())
                .build();
    }
}