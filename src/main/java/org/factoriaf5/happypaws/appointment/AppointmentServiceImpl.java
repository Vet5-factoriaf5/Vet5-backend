package org.factoriaf5.happypaws.appointment;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.factoriaf5.happypaws.user.UserEntity;
import org.factoriaf5.happypaws.user.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// Contiene lógica de negocio para la gestión de citas
// Incluye: validaciones, control de máximo diario, colisiones horarias, envío de emails
@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository repository;
    private final AppointmentMapper mapper;
    private final JavaMailSender mailSender;
    private final UserRepository userRepository; // Para obtener el email real del usuario

    private static final int MAX_APPOINTMENTS_PER_DAY = 10;

    @Override
    public AppointmentDTOResponse create(AppointmentDTORequest dto) {
        AppointmentEntity entity = mapper.toEntity(dto);

        // Validaciones de objeto (fecha futura, motivo no vacío, etc.)
        AppointmentValidator.validate(entity);

        // Validación de máximo de citas por día
        LocalDate date = entity.getDateTime().toLocalDate();
        long count = repository.findByDateTimeBetween(date.atStartOfDay(), date.plusDays(1).atStartOfDay()).size();
        if (count >= MAX_APPOINTMENTS_PER_DAY) {
            throw new AppointmentException("Se alcanzó el máximo de citas para este día");
        }

        // Validación de colisión exacta en la misma hora
        LocalDateTime slot = entity.getDateTime();
        boolean exists = repository.findByDateTimeBetween(slot, slot.plusMinutes(59)).size() > 0;
        if (exists) {
            throw new AppointmentException("La hora seleccionada ya está ocupada");
        }

        AppointmentEntity saved = repository.save(entity);

        // ------------------ ENVÍO DE EMAIL ------------------
        try {
            // Obtenemos el usuario desde la BD
            UserEntity user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            String email = user.getEmail();

            // Creamos el mensaje de confirmación
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email); // Aquí usamos el email real del usuario
            message.setSubject("Confirmación de cita en HappyPaws");
            message.setText("Hola " + user.getFullName() + ",\n\n" +
                    "Tu cita ha sido registrada para el " + entity.getDateTime() +
                    ".\nMotivo: " + entity.getReason() + "\n\n" +
                    "Gracias por confiar en HappyPaws.");

            // Enviamos el email
            mailSender.send(message);

        } catch (Exception e) {
            // Si falla el envío de email, no bloqueamos la creación de la cita
            System.out.println("Error enviando email: " + e.getMessage());
        }

        return mapper.toDTO(saved);
    }

    @Override
    public AppointmentDTOResponse update(Long id, AppointmentDTORequest dto) {
        AppointmentEntity existing = repository.findById(id)
                .orElseThrow(() -> new AppointmentException("Cita no encontrada"));

        existing.setDateTime(dto.getDateTime());
        existing.setType(dto.getType());
        existing.setReason(dto.getReason());

        AppointmentValidator.validate(existing);

        AppointmentEntity updated = repository.save(existing);
        return mapper.toDTO(updated);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new AppointmentException("Cita no encontrada");
        }
        repository.deleteById(id);
    }

    @Override
    public AppointmentDTOResponse getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new AppointmentException("Cita no encontrada"));
    }

    @Override
    public List<AppointmentDTOResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTOResponse> getByPatient(Long patientId) {
        return repository.findByPatient_Id(patientId).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}