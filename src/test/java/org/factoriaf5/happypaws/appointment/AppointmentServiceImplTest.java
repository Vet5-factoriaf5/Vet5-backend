package org.factoriaf5.happypaws.appointment;

import org.factoriaf5.happypaws.patient.PatientEntity;
import org.factoriaf5.happypaws.user.UserEntity;
import org.factoriaf5.happypaws.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppointmentServiceImplTest {

    private AppointmentRepository repository;
    private AppointmentMapper mapper;
    private JavaMailSender mailSender;
    private UserRepository userRepository;
    private AppointmentServiceImpl service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(AppointmentRepository.class);
        mapper = Mockito.mock(AppointmentMapper.class);
        mailSender = Mockito.mock(JavaMailSender.class);
        userRepository = Mockito.mock(UserRepository.class);
        service = new AppointmentServiceImpl(repository, mapper, mailSender, userRepository);
    }

    @Test
    void createAppointment_success() {
        // DTO de prueba
        AppointmentDTORequest dto = AppointmentDTORequest.builder()
                .patientId(1L)
                .userId(1L)
                .dateTime(LocalDateTime.now().plusDays(1))
                .type(AppointmentType.ESTANDAR)
                .reason("Chequeo")
                .build();

        // Entity correspondiente
        PatientEntity patient = new PatientEntity();
        patient.setId(dto.getPatientId());

        AppointmentEntity entity = AppointmentEntity.builder()
                .id(1L)
                .patient(patient)
                .dateTime(dto.getDateTime())
                .type(dto.getType())
                .reason(dto.getReason())
                .status(AppointmentStatus.PENDING)
                .build();

        AppointmentDTOResponse responseDto = AppointmentDTOResponse.builder()
                .id(1L)
                .patientId(1L)
                .dateTime(dto.getDateTime())
                .type(dto.getType())
                .reason(dto.getReason())
                .status(AppointmentStatus.PENDING)
                .build();

        // Mocks del mapper y repositorio
        Mockito.when(mapper.toEntity(dto)).thenReturn(entity);
        Mockito.when(repository.save(entity)).thenReturn(entity);
        Mockito.when(mapper.toDTO(entity)).thenReturn(responseDto);

        // Mock para userRepository y mailSender
        UserEntity user = new UserEntity();
        user.setId(dto.getUserId());
        user.setEmail("user@mail.com");
        user.setFullName("Juan Perez");

        Mockito.when(userRepository.findById(dto.getUserId())).thenReturn(java.util.Optional.of(user));
        Mockito.doNothing().when(mailSender).send(Mockito.any(SimpleMailMessage.class));

        // Llamada al servicio
        AppointmentDTOResponse result = service.create(dto);

        // Asserts
        assertEquals(1L, result.getId());
        assertEquals("Chequeo", result.getReason());
    }
}