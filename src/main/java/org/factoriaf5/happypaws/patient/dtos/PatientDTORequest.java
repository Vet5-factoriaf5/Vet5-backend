package org.factoriaf5.happypaws.patient.dtos;

import java.time.LocalDate;

public record PatientDTORequest(String name,
        LocalDate birthDate,
        String breed,
        String gender,
        String idNumber,
        String tutorName,
        String tutorEmail,
        String tutorPhone
// tutorUserId
) {

}
