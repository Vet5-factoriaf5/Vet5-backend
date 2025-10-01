package org.factoriaf5.happypaws.appointment;

import lombok.*;
import java.time.LocalDateTime;

// devolvemos patientId en lugar de un objeto completo para simplificar la API
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