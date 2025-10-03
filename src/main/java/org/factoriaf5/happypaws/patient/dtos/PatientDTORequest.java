package org.factoriaf5.happypaws.patient.dtos;

public record PatientDTORequest(
        String name,
        int age,
        String breed,
        String gender,
        Long idUser) {
}
