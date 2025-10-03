package org.factoriaf5.happypaws.appointment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AppointmentControllerTest {

    @Mock
    private AppointmentService service;

    @InjectMocks
    private AppointmentController controller;

    private AppointmentDTOResponse response;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        response = AppointmentDTOResponse.builder()
                .id(1L)
                .patientId(1L)
                .dateTime(LocalDateTime.now())
                .type(AppointmentType.ESTANDAR)
                .reason("Consulta general")
                .status(AppointmentStatus.PENDING)
                .build();
    }

    @Test
    void createAppointment_returnsOk() {
        when(service.create(any(AppointmentDTORequest.class))).thenReturn(response);

        ResponseEntity<AppointmentDTOResponse> result = controller.create(
                new AppointmentDTORequest()
        );

        assertEquals(200, result.getStatusCode().value());
        assertNotNull(result.getBody());
        assertEquals(response.getId(), result.getBody().getId());

        // Ese warning es porque el método getBody() en Spring está anotado con @Nullable.
        // El compilador te avisa que podría devolver null aunque lo estamos simulando con mocks → nunca va a ser null.
    }

    @Test
    void getAllAppointments_returnsList() {
        when(service.getAll()).thenReturn(List.of(response));

        ResponseEntity<List<AppointmentDTOResponse>> result = controller.getAll();

        assertEquals(200, result.getStatusCode().value());
        assertNotNull(result.getBody());
        assertEquals(1, result.getBody().size());
    }
}