package org.factoriaf5.happypaws.treatment;

import jakarta.persistence.*;
import lombok.Data;
import org.factoriaf5.happypaws.temp.Patient;

import java.time.LocalDate;

// Relación ManyToOne con Patient. Contiene descripción y fecha del tratamiento.

@Data
@Entity
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(nullable = false)
    private String description; // descripción del tratamiento

    @Column(nullable = false)
    private LocalDate treatmentDate; // fecha del tratamiento
}
