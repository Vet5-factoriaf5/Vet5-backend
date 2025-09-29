package org.factoriaf5.happypaws.patient;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "patients")
@Data
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;
    private LocalDate birthDate;
    private String breed;
    private String gender;
    @Column(unique = true)
    private String idNumber;

    private String tutorName;
    private String tutorEmail;
    private String tutorPhone;

    // @ManyToOne
    // @JoinColumn(name = "tutor_user_id", nullable = false)
    // private UserEntity tutor;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
