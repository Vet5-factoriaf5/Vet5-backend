package org.factoriaf5.happypaws.appointment;
import org.factoriaf5.happypaws.patient.PatientEntity;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientEntity patient;

    private LocalDateTime dateTime; // Fecha y hora de la cita

    @Enumerated(EnumType.STRING)
    private AppointmentType type; // ESTANDAR / URGENCIA

    private String reason; // Motivo de la cita

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status; // PENDIENTE / ATENDIDA / PASADA
}