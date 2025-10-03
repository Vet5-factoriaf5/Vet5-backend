package org.factoriaf5.happypaws.patient;

import java.time.LocalDateTime;

public class PatientMapper {

    public static PatientEntity toEntity(PatientDTORequest dtoRequest) {
        return PatientEntity.builder()
                .name(dtoRequest.name())
                .age(dtoRequest.age())
                .breed(dtoRequest.breed())
                .gender(dtoRequest.gender())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static PatientDTOResponse toDTO(PatientEntity entity) {
        return new PatientDTOResponse(
                entity.getId(),
                entity.getName(),
                entity.getAge(),
                entity.getBreed(),
                entity.getGender(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
