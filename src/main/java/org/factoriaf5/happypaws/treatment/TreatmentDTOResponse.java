package org.factoriaf5.happypaws.treatment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TreatmentDTOResponse {

    private Long id;
    private Long patientId;
    private String description;
    private LocalDate treatmentDate;
}