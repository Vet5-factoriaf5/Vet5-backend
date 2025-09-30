package org.factoriaf5.happypaws.patient;

import java.security.Principal;

import org.factoriaf5.happypaws.patient.dtos.PatientDTORequest;
import org.factoriaf5.happypaws.patient.dtos.PatientDTOResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
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

        PatientDTOResponse patientStored = patientService.createPatient(dto);

        if (patientStored == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.status(HttpStatus.CREATED).body(patientStored);
    }
}
