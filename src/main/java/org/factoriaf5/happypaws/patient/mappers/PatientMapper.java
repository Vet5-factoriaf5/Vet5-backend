package org.factoriaf5.happypaws.patient.mappers;

import java.time.LocalDateTime;

import org.factoriaf5.happypaws.patient.PatientEntity;
import org.factoriaf5.happypaws.patient.dtos.PatientDTORequest;
import org.factoriaf5.happypaws.patient.dtos.PatientDTOResponse;

public class PatientMapper {

    public static PatientEntity toEntity(PatientDTORequest dtoRequest) {
        return PatientEntity.builder()
                .name(dtoRequest.name())
                .birthDate(dtoRequest.birthDate())
                .breed(dtoRequest.breed())
                .gender(dtoRequest.gender())
                .idNumber(dtoRequest.idNumber())
                .tutorName(dtoRequest.tutorName())
                .tutorEmail(dtoRequest.tutorEmail())
                .tutorPhone(dtoRequest.tutorPhone())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static PatientDTOResponse toDTO(PatientEntity entity) {
        return new PatientDTOResponse(
                entity.getId(),
                entity.getName(),
                entity.getBirthDate(),
                entity.getBreed(),
                entity.getGender(),
                entity.getIdNumber(),
                entity.getTutorName(),
                entity.getTutorEmail(),
                entity.getTutorPhone(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
