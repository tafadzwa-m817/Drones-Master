package com.drones.service;

import com.drones.service.dto.DroneDto;
import com.drones.service.dto.MedicationDto;

import java.util.List;


public interface DroneService {

    /**
     * This method registers Drone from DroneRequest
     * @param droneRequest
     * @return DroneDto
     */
    DroneDto registerDrone(DroneRequest droneRequest);

    /**
     * This method retrieves Drone with droneId
     * @param droneId
     * @return DroneDto
     */
    DroneDto findDroneById(Long droneId);

    /**
     * loadDroneWithMedication attempts to load drone with medication
     * @param medicationCode
     * @return DroneDto
     */
    DroneDto loadDroneWithMedication(String medicationCode);

    /**
     * checkAvailableDronesForLoading attempts to check for available drones to load
     * @param
     * @return List<DroneDto>
     */
    List<DroneDto> checkAvailableDronesForLoading();

    /**
     * checkBatteryCapacity attempts to check battery capacity for a given drone
     * @param droneId
     * @return Integer
     */
    Integer checkBatteryCapacity(Long droneId);

    /**
     * checkMedicationForDrone attempts to check medication for a given drone
     * @param droneId
     * @return MedicationDto
     */
    MedicationDto checkMedicationForDrone(Long droneId);
}
