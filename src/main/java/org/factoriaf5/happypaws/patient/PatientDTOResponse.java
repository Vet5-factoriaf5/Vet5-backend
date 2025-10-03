package org.factoriaf5.happypaws.patient;

import java.time.LocalDateTime;

public record PatientDTOResponse(Long id,
                String name,
                int age,
                String breed,
                String gender,
                LocalDateTime createdAt,
                LocalDateTime updatedAt) {

}
