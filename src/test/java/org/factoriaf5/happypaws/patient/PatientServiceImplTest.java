package org.factoriaf5.happypaws.patient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.factoriaf5.happypaws.patient.dtos.PatientDTORequest;
import org.factoriaf5.happypaws.patient.dtos.PatientDTOResponse;
import org.factoriaf5.happypaws.user.UserEntity;
import org.factoriaf5.happypaws.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PatientServiceImplTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PatientServiceImpl patientService;

    @Test
    @DisplayName("Should create patient and link it to user")
    void testCreatePatient() {
        PatientDTORequest request = new PatientDTORequest("Bobby", 5, "Labrador", "Male", 1L);
        UserEntity user = new UserEntity();
        user.setId(1L);

        PatientEntity savedPatient = new PatientEntity();
        savedPatient.setId(100L);
        savedPatient.setName("Bobby");
        savedPatient.setTutor(user);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(patientRepository.save(any(PatientEntity.class))).thenReturn(savedPatient);

        PatientDTOResponse response = patientService.createPatient(request);

        assertThat(response.id()).isEqualTo(100L);
        assertThat(response.name()).isEqualTo("Bobby");
        verify(userRepository).findById(1L);
        verify(patientRepository).save(any(PatientEntity.class));
    }
}
