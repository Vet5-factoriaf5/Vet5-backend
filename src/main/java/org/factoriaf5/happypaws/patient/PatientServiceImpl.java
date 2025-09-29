package org.factoriaf5.happypaws.patient;

import org.factoriaf5.happypaws.patient.dtos.PatientDTORequest;
import org.factoriaf5.happypaws.patient.dtos.PatientDTOResponse;
import org.factoriaf5.happypaws.patient.mappers.PatientMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl {

    private final PatientRepository repository;

    public PatientDTOResponse createPatient(PatientDTORequest dto) {
        if (repository.existsByIdNumber(dto.idNumber())) {
            throw new RuntimeException("Paciente con este ID ya existe");
        }

        PatientEntity patient = PatientMapper.toEntity(dto);
        PatientEntity savedPatient = repository.save(patient);
        return PatientMapper.toDTO(savedPatient);
    }
}
