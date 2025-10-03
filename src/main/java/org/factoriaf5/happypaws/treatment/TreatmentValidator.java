package org.factoriaf5.happypaws.treatment;

import org.springframework.stereotype.Component;
import java.time.LocalDate;


@Component
public class TreatmentValidator {

    public void validate(TreatmentDTORequest dto) {
        if (dto.getTreatmentDate().isBefore(LocalDate.now())) {
            throw new TreatmentException("La fecha del tratamiento no puede ser anterior a hoy");
        }
        if (dto.getDescription().isBlank()) {
            throw new TreatmentException("La descripción es obligatoria");
        }
        if (dto.getPatientId() == null) {
            throw new TreatmentException("El paciente es obligatorio");
        }
    }
}