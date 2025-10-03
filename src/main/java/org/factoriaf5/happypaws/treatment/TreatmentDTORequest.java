package org.factoriaf5.happypaws.treatment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TreatmentDTORequest {

    @NotNull
    private Long patientId;

    @NotBlank
    private String description;

    @NotNull
    private LocalDate treatmentDate;
}