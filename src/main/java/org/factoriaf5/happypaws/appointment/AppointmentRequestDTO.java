package org.factoriaf5.happypaws.appointment;

import lombok.*;
import java.time.LocalDateTime;

// DTO que recibe el frontend al crear/editar una cita
// Incluimos patientId porque todavía no tenemos la relación User → Patient real
// Más adelante (cuando yely suba PatientEntity) podremos sacar esto automáticamente
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentRequestDTO {

    private Long patientId;       // ID del paciente asociado a la cita (temporal)
    private Long userId;          // ID del usuario dueño de la cita → lo necesitamos para enviar el email
    private LocalDateTime dateTime;
    private AppointmentType type;
    private String reason;
}