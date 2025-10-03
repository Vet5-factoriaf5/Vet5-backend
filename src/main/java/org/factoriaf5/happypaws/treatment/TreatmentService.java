package org.factoriaf5.happypaws.treatment;

import java.util.List;


public interface TreatmentService {

    TreatmentDTOResponse createTreatment(TreatmentDTORequest dto);
    TreatmentDTOResponse updateTreatment(Long id, TreatmentDTORequest dto);
    void deleteTreatment(Long id);
    TreatmentDTOResponse getTreatmentById(Long id);
    List<TreatmentDTOResponse> getTreatmentsByPatient(Long patientId);
    List<TreatmentDTOResponse> getAllTreatments();
}
