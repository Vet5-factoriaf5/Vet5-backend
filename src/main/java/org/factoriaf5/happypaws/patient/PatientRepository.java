package org.factoriaf5.happypaws.patient;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    boolean existsByIdNumber(String idNumber);
}
