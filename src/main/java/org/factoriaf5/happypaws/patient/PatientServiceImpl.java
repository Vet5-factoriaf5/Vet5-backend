package org.factoriaf5.happypaws.patient;

import org.factoriaf5.happypaws.patient.dtos.PatientDTORequest;
import org.factoriaf5.happypaws.patient.dtos.PatientDTOResponse;
import org.factoriaf5.happypaws.patient.mappers.PatientMapper;
import org.factoriaf5.happypaws.user.UserEntity;
import org.factoriaf5.happypaws.user.UserRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl {

    private final PatientRepository repository;
    private final UserRepository userRepository;

    public PatientDTOResponse createPatient(PatientDTORequest dto) {

        // crear patiente
        PatientEntity newPatient = PatientMapper.toEntity(dto);
        // recuperar usuario
        UserEntity user = userRepository.findById(dto.idUser()).orElseThrow();
        //
        newPatient.setTutor(user);

        PatientEntity savedPatient = repository.save(newPatient);
        return PatientMapper.toDTO(savedPatient);
    }
}
