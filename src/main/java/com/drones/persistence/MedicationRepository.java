package com.drones.persistence;

import com.drones.domain.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MedicationRepository extends JpaRepository<Medication, Long> {
    Optional<Medication> findByCode(String code);
}
