package org.factoriaf5.happypaws.patient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.factoriaf5.happypaws.patient.dtos.PatientDTORequest;
import org.factoriaf5.happypaws.patient.dtos.PatientDTOResponse;
import org.factoriaf5.happypaws.user.UserEntity;
import org.factoriaf5.happypaws.user.UserService;
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
    private UserService userService;

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

        when(userService.getUserById(1L)).thenReturn(user);
        when(patientRepository.save(any(PatientEntity.class))).thenReturn(savedPatient);

        PatientDTOResponse response = patientService.storeEntity(request);

        assertThat(response.id()).isEqualTo(100L);
        assertThat(response.name()).isEqualTo("Bobby");
        verify(userService).getUserById(1L);
        verify(patientRepository).save(any(PatientEntity.class));
    }

    @Test
    @DisplayName("Should return list of patient DTOs")
    void testGetEntities() {
        PatientEntity patient1 = new PatientEntity();
        patient1.setId(1L);
        patient1.setName("Bobby");

        PatientEntity patient2 = new PatientEntity();
        patient2.setId(2L);
        patient2.setName("Anna");

        List<PatientEntity> patients = List.of(patient1, patient2);

        when(patientRepository.findAll()).thenReturn(patients);

        List<PatientDTOResponse> responseList = patientService.getEntities();

        assertThat(responseList).hasSize(2);
        assertThat(responseList.get(0).id()).isEqualTo(1L);
        assertThat(responseList.get(0).name()).isEqualTo("Bobby");
        assertThat(responseList.get(1).id()).isEqualTo(2L);
        assertThat(responseList.get(1).name()).isEqualTo("Anna");

        verify(patientRepository).findAll();
    }

    @Test
    @DisplayName("Should return patient DTO when patient exists")
    void testShowById_Found() {
        PatientEntity patient = new PatientEntity();
        patient.setId(1L);
        patient.setName("Bobby");

        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        PatientDTOResponse response = patientService.showById(1L);

        assertThat(response.id()).isEqualTo(1L);
        assertThat(response.name()).isEqualTo("Bobby");

        verify(patientRepository).findById(1L);
    }

    @Test
    @DisplayName("Should throw exception when patient not found")
    void testShowById_NotFound() {
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> patientService.showById(1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Patient not found");

        verify(patientRepository).findById(1L);
    }
}
