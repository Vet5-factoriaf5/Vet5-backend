package org.factoriaf5.happypaws.treatment;

import lombok.RequiredArgsConstructor;
import org.factoriaf5.happypaws.patient.PatientEntity;
import org.factoriaf5.happypaws.patient.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TreatmentServiceImpl implements TreatmentService {

    private final TreatmentRepository treatmentRepository;
    private final PatientRepository patientRepository; // repositorio para buscar pacientes
    private final TreatmentMapper mapper;
    private final TreatmentValidator validator;

    @Override
    public TreatmentDTOResponse createTreatment(TreatmentDTORequest dto) {
        validator.validate(dto); // validar reglas de negocio
        PatientEntity patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new TreatmentException("Paciente no encontrado"));
        Treatment treatment = mapper.toEntity(dto, patient);
        return mapper.toDTO(treatmentRepository.save(treatment));
    }

    @Override
    public TreatmentDTOResponse updateTreatment(Long id, TreatmentDTORequest dto) {
        validator.validate(dto);
        Treatment treatment = treatmentRepository.findById(id)
                .orElseThrow(() -> new TreatmentException("Tratamiento no encontrado"));
        PatientEntity patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new TreatmentException("Paciente no encontrado"));
        treatment.setDescription(dto.getDescription());
        treatment.setTreatmentDate(dto.getTreatmentDate());
        treatment.setPatient(patient);
        return mapper.toDTO(treatmentRepository.save(treatment));
    }

    @Override
    public void deleteTreatment(Long id) {
        Treatment treatment = treatmentRepository.findById(id)
                .orElseThrow(() -> new TreatmentException("Tratamiento no encontrado"));
        treatmentRepository.delete(treatment);
    }

    @Override
    public TreatmentDTOResponse getTreatmentById(Long id) {
        Treatment treatment = treatmentRepository.findById(id)
                .orElseThrow(() -> new TreatmentException("Tratamiento no encontrado"));
        return mapper.toDTO(treatment);
    }

    @Override
    public List<TreatmentDTOResponse> getTreatmentsByPatient(Long patientId) {
        return treatmentRepository.findByPatientId(patientId)
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<TreatmentDTOResponse> getAllTreatments() {
        return treatmentRepository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}