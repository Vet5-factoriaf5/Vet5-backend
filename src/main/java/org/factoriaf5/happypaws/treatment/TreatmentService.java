package org.factoriaf5.happypaws.treatment;

import org.factoriaf5.happypaws.treatment.TreatmentDTORequest;
import org.factoriaf5.happypaws.treatment.TreatmentDTOResponse;

import java.util.List;

/**
 * Interfaz de servicio para Treatment.
 * Define métodos de negocio y reglas de acceso.
 */
public interface TreatmentService {

    TreatmentDTOResponse createTreatment(TreatmentDTORequest dto);
    TreatmentDTOResponse updateTreatment(Long id, TreatmentDTORequest dto);
    void deleteTreatment(Long id);
    TreatmentDTOResponse getTreatmentById(Long id);
    List<TreatmentDTOResponse> getTreatmentsByPatient(Long patientId);
    List<TreatmentDTOResponse> getAllTreatments();
}
