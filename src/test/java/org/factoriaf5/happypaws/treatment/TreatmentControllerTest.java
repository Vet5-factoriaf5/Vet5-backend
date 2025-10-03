package org.factoriaf5.happypaws.treatment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TreatmentControllerTest {

    @Mock
    private TreatmentService service;

    @InjectMocks
    private TreatmentController controller;

    private TreatmentDTORequest request;
    private TreatmentDTOResponse response;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        request = TreatmentDTORequest.builder()
                .patientId(1L)
                .description("Vacunación")
                .treatmentDate(LocalDate.now())
                .build();

        response = TreatmentDTOResponse.builder()
                .id(1L)
                .patientId(1L)
                .description("Vacunación")
                .treatmentDate(request.getTreatmentDate())
                .build();
    }

    @Test
    void createTreatment_returnsCreated() {
        when(service.createTreatment(request)).thenReturn(response);

        ResponseEntity<TreatmentDTOResponse> result = controller.createTreatment(request);

        assertEquals(201, result.getStatusCode().value());
        assertNotNull(result.getBody());
        assertEquals(response.getDescription(), result.getBody().getDescription());
    }

    @Test
    void getTreatmentById_returnsTreatment() {
        when(service.getTreatmentById(1L)).thenReturn(response);

        ResponseEntity<TreatmentDTOResponse> result = controller.getTreatmentById(1L);

        assertEquals(200, result.getStatusCode().value());
        assertNotNull(result.getBody());
        assertEquals(response.getId(), result.getBody().getId());
    }

    @Test
    void getAllTreatments_returnsList() {
        when(service.getAllTreatments()).thenReturn(List.of(response));

        ResponseEntity<List<TreatmentDTOResponse>> result = controller.getAllTreatments();

        assertEquals(200, result.getStatusCode().value());
        assertNotNull(result.getBody());
        assertEquals(1, result.getBody().size());
    }
}