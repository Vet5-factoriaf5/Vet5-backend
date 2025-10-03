package org.factoriaf5.happypaws.patient;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class PatientEntityTest {
    @Test
    void builderSetsFields() {
        PatientEntity patient = PatientEntity.builder()
                .name("Bella")
                .age(3)
                .breed("Golden Retriever")
                .gender("Female")
                .build();

        assertThat(patient.getName()).isEqualTo("Bella");
        assertThat(patient.getAge()).isEqualTo(3);
        assertThat(patient.getBreed()).isEqualTo("Golden Retriever");
        assertThat(patient.getGender()).isEqualTo("Female");
    }
}
