package org.factoriaf5.happypaws.patient;

import java.time.LocalDateTime;

import org.factoriaf5.happypaws.user.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "patients")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_patient")
    private Long id;

    private String name;
    private int age;
    private String breed;
    private String gender;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity tutor;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
