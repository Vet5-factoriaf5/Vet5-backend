package org.factoriaf5.happypaws.treatment;

import org.factoriaf5.happypaws.treatment.TreatmentDTORequest;
import org.springframework.stereotype.Component;
import org.factoriaf5.happypaws.treatment.TreatmentException;

import java.time.LocalDate;

/**
 * Valida reglas de negocio para Treatment.
 * Por ejemplo, que la fecha no sea futura y que los campos obligatorios existan.
 */
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