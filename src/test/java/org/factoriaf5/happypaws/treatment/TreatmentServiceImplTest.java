package org.factoriaf5.happypaws.treatment;

import org.factoriaf5.happypaws.patient.PatientEntity;
import org.factoriaf5.happypaws.patient.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TreatmentServiceImplTest {

    private TreatmentRepository repository;
    private PatientRepository patientRepository;
    private TreatmentMapper mapper;
    private TreatmentValidator validator;
    private TreatmentServiceImpl service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(TreatmentRepository.class);
        patientRepository = Mockito.mock(PatientRepository.class);
        mapper = Mockito.mock(TreatmentMapper.class);
        validator = Mockito.mock(TreatmentValidator.class);

        service = new TreatmentServiceImpl(repository, patientRepository, mapper, validator);
    }

    @Test
    void createTreatment_success() {
        TreatmentDTORequest dto = new TreatmentDTORequest(1L, "Vacuna", LocalDate.now());
        PatientEntity patient = new PatientEntity();
        Treatment treatmentEntity = new Treatment();
        TreatmentDTOResponse response = new TreatmentDTOResponse();
        response.setId(1L);
        response.setDescription("Vacuna");

        Mockito.when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        Mockito.when(mapper.toEntity(dto, patient)).thenReturn(treatmentEntity);
        Mockito.when(repository.save(treatmentEntity)).thenReturn(treatmentEntity);
        Mockito.when(mapper.toDTO(treatmentEntity)).thenReturn(response);

        TreatmentDTOResponse result = service.createTreatment(dto);

        assertEquals(1L, result.getId());
        assertEquals("Vacuna", result.getDescription());
    }

    @Test
    void getAllTreatments_success() {
        Treatment treatmentEntity = new Treatment();
        TreatmentDTOResponse response = new TreatmentDTOResponse();
        response.setId(1L);
        response.setDescription("Vacuna");

        Mockito.when(repository.findAll()).thenReturn(List.of(treatmentEntity));
        Mockito.when(mapper.toDTO(treatmentEntity)).thenReturn(response);

        List<TreatmentDTOResponse> results = service.getAllTreatments();

        assertEquals(1, results.size());
        assertEquals("Vacuna", results.get(0).getDescription());
    }
}