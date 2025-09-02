package com.drones.service.medication;

import com.drones.service.dto.MedicationDto;


public interface MedicationService {

    /**
     * saveMedication method attempts to save medication with MedicationRequest
     * @param medicationRequest
     * @return MedicationDto
     */
    MedicationDto saveMedication(MedicationRequest medicationRequest);

    /**
     * findMedicationById method attempts to retrieve medication by id
     * @param id
     * @return MedicationDto
     */
    MedicationDto findMedicationById(Long id);
}
