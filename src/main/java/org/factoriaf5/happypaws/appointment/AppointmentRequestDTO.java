package org.factoriaf5.happypaws.appointment;
// TODO: import temporal sustituir por la entidad definitiva una vez Yely la suba
import org.factoriaf5.happypaws.temp.Patient;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentRequestDTO {
    private Patient patient;
    private LocalDateTime dateTime;
    private AppointmentType type;
    private String reason;
    private AppointmentStatus status;
}