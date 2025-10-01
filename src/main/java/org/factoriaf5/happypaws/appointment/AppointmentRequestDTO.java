package org.factoriaf5.happypaws.appointment;

import lombok.*;
import java.time.LocalDateTime;

//TODO Usamos solo patientId para desacoplar de la entidad Patient temporal
// Cuando PatientEntity real esté lista  en el Mapper usaremos patientRepository.findById(patientId)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentRequestDTO {

    private Long patientId;       // Solo el ID del paciente
    private LocalDateTime dateTime;
    private AppointmentType type;
    private String reason;
}