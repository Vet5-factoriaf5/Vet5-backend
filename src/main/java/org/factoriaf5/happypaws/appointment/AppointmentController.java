package org.factoriaf5.happypaws.appointment;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService service;

    @GetMapping("/all")
    public List<AppointmentEntity> getAllAppointments() {
        return service.getAllAppointments(); // Admin
    }

    @GetMapping("/my")
    public List<AppointmentEntity> getMyAppointments(@RequestParam Long idUser) {
        return service.getAppointmentsByUser(idUser); // Cliente
    }

    @PostMapping
    public AppointmentEntity createAppointment(@RequestBody AppointmentEntity appointment) {
        return service.createAppointment(appointment);
    }

    @PutMapping("/{id}")
    public AppointmentEntity updateAppointment(@PathVariable Long id, @RequestBody AppointmentEntity appointment) {
        return service.updateAppointment(id, appointment);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        service.deleteAppointment(id);
    }
}