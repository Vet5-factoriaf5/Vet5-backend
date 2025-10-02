package org.factoriaf5.happypaws.treatment;

import lombok.RequiredArgsConstructor;
import lombok.val;

import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/treatments")
@RequiredArgsConstructor
public class TreatmentController {

    private final TreatmentService treatmentService;

    @PostMapping
    public ResponseEntity<TreatmentDTOResponse> createTreatment(@Valid @RequestBody TreatmentDTORequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(treatmentService.createTreatment(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TreatmentDTOResponse> updateTreatment(@PathVariable Long id,
       @Valid @RequestBody TreatmentDTORequest dto) {
       return ResponseEntity.ok(treatmentService.updateTreatment(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTreatment(@PathVariable Long id) {
        treatmentService.deleteTreatment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreatmentDTOResponse> getTreatmentById(@PathVariable Long id) {
        return ResponseEntity.ok(treatmentService.getTreatmentById(id));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<TreatmentDTOResponse>> getTreatmentsByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(treatmentService.getTreatmentsByPatient(patientId));
    }

    @GetMapping
    public ResponseEntity<List<TreatmentDTOResponse>> getAllTreatments() {
        return ResponseEntity.ok(treatmentService.getAllTreatments());
    }
}