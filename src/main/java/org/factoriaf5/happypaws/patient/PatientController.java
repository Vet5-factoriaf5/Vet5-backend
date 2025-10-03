package org.factoriaf5.happypaws.patient;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api-endpoint}/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientServiceImpl patientService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PatientDTOResponse> createPatient(@RequestBody PatientDTORequest dto) {

        if (dto.name().isBlank())
            return ResponseEntity.badRequest().build();

        PatientDTOResponse patientStored = patientService.storeEntity(dto);

        if (patientStored == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.status(HttpStatus.CREATED).body(patientStored);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<PatientDTOResponse>> getAllPatients() {
        return ResponseEntity.ok(patientService.getEntities());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PatientDTOResponse> getPatientById(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.showById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PatientDTOResponse> updatePatient(@PathVariable Long id,
            @RequestBody PatientDTORequest dto) {
        PatientDTOResponse updated = patientService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
