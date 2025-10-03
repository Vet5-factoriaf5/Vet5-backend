package org.factoriaf5.happypaws.treatment;

import org.springframework.stereotype.Component;

@Component
public class TreatmentMapper {

    public Treatment toEntity(TreatmentDTORequest dto, org.factoriaf5.happypaws.patient.PatientEntity patient) {
        Treatment treatment = new Treatment();
        treatment.setPatient(patient);
        treatment.setDescription(dto.getDescription());
        treatment.setTreatmentDate(dto.getTreatmentDate());
        return treatment;
    }

    public TreatmentDTOResponse toDTO(Treatment treatment) {
        TreatmentDTOResponse dto = new TreatmentDTOResponse();
        dto.setId(treatment.getId());
        dto.setPatientId(treatment.getPatient().getId());
        dto.setDescription(treatment.getDescription());
        dto.setTreatmentDate(treatment.getTreatmentDate());
        return dto;
    }
}