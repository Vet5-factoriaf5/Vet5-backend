package org.factoriaf5.happypaws.patient.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PatientDTOResponse(Long id,
        String name,
        LocalDate birthDate,
        String breed,
        String gender,
        String idNumber,
        String tutorName,
        String tutorEmail,
        String tutorPhone,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

}
