package org.factoriaf5.happypaws.patient;

import java.util.List;

import org.factoriaf5.happypaws.implementations.IGenericService;
import org.factoriaf5.happypaws.user.UserEntity;
import org.factoriaf5.happypaws.user.UserService;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements IGenericService<PatientDTOResponse, PatientDTORequest> {

    private final PatientRepository repository;
    private final UserService userService;

    @Override
    public List<PatientDTOResponse> getEntities() {
        return repository.findAll()
                .stream()
                .map(PatientMapper::toDTO)
                .toList();
    }

    @Override
    public PatientDTOResponse storeEntity(PatientDTORequest dto) {

        PatientEntity newPatient = PatientMapper.toEntity(dto);
        UserEntity user = userService.getUserById(dto.idUser());
        newPatient.setTutor(user);

        PatientEntity savedPatient = repository.save(newPatient);
        return PatientMapper.toDTO(savedPatient);
    }

    @Override
    public PatientDTOResponse showById(Long id) {
        return repository.findById(id)
                .map(PatientMapper::toDTO)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));
    }

    @Override
    public PatientDTOResponse update(Long id, PatientDTORequest dto) {
        PatientEntity existing = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));

        existing.setName(dto.name());
        existing.setAge(dto.age());
        existing.setBreed(dto.breed());
        existing.setGender(dto.gender());

        UserEntity user = userService.getUserById(dto.idUser());
        existing.setTutor(user);

        PatientEntity updated = repository.save(existing);
        return PatientMapper.toDTO(updated);
    }

    @Override
    public void delete(Long id) {
        PatientEntity existing = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));
        repository.delete(existing);
    }
}
