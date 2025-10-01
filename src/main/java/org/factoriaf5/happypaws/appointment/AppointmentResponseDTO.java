package org.factoriaf5.happypaws.appointment;

import lombok.*;
import java.time.LocalDateTime;

// DTO que devolvemos al frontend tras crear/consultar citas
// Usamos patientId para no acoplar a la entidad Patient completa todavía
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentResponseDTO {

    private Long id;
    private Long patientId;
    private LocalDateTime dateTime;
    private AppointmentType type;
    private String reason;
    private AppointmentStatus status;
}
