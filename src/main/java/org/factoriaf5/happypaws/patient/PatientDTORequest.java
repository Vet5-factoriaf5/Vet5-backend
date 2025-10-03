package org.factoriaf5.happypaws.patient;

public record PatientDTORequest(
        String name,
        int age,
        String breed,
        String gender,
        Long idUser) {
}
