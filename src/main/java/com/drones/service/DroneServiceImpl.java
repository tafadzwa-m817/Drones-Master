package com.drones.service;

import com.drones.domain.Drone;
import com.drones.domain.Medication;
import com.drones.errors.DroneLoadingFailedException;
import com.drones.errors.DroneNotFoundException;
import com.drones.persistence.DroneRepository;
import com.drones.persistence.MedicationRepository;
import com.drones.service.dto.DroneDto;
import com.drones.service.dto.MedicationDto;
import com.drones.util.EntityToDtoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.drones.util.AppConstants.DRONE_NOT_FOUND;
import static com.drones.util.AppConstants.LOADING_DRONE_FAILED;
import static com.drones.util.EntityToDtoUtil.convertToDroneDto;
import static com.drones.util.EntityToDtoUtil.convertToMedicationDto;
import static com.drones.util.State.IDLE;
import static com.drones.util.State.LOADING;
import static com.drones.util.ValidationUtil.validateWeightLimit;
import static java.util.Objects.requireNonNull;


@Service
@Slf4j
@RequiredArgsConstructor
public class DroneServiceImpl implements DroneService {
    private final ModelMapper modelMapper;
    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;
    @Override
    public DroneDto registerDrone(DroneRequest droneRequest) {
        requireNonNull(droneRequest, "DroneRequest cannot be null");

        validateWeightLimit(droneRequest.getWeightLimit());

        log.info("Registering drone with, droneRequest = {}", droneRequest);
        Drone drone = Drone.builder()
                .serialNumber(droneRequest.getSerialNumber())
                .model(droneRequest.getModel())
                .state(droneRequest.getState())
                .weightLimit(droneRequest.getWeightLimit())
                .batteryCapacity(droneRequest.getBatteryCapacity())
                .build();
        Drone savedDrone = droneRepository.save(drone);
        return convertToDroneDto(savedDrone, modelMapper);
    }
    @Override
    public DroneDto findDroneById(Long droneId) {
        return convertToDroneDto(
                droneRepository.findById(droneId)
                        .orElseThrow(() -> new DroneNotFoundException(
                                String.format(DRONE_NOT_FOUND, droneId)
                        )), modelMapper);
    }
    @Override
    public DroneDto loadDroneWithMedication(String medicationCode) {
        requireNonNull(medicationCode, "medication code cannot be null");

        Optional<Medication> medication = medicationRepository.findByCode(medicationCode);
        Optional<Drone> foundDrone = droneRepository.findAllByState(IDLE)
                .stream().filter(drone -> drone.getBatteryCapacity() > 25)
                .filter(drone -> drone.getWeightLimit() <= medication.get().getWeight())
                .findFirst();

        if (!foundDrone.isPresent()){
            throw new DroneLoadingFailedException(LOADING_DRONE_FAILED);
        }
        foundDrone.get().setState(LOADING);
        foundDrone.get().setMedication(medication.get());
        return convertToDroneDto(droneRepository.save(foundDrone.get()), modelMapper);
    }

    @Override
    public List<DroneDto> checkAvailableDronesForLoading() {
        return droneRepository.findAllByState(IDLE)
                .stream().filter(drone -> drone.getBatteryCapacity() > 25)
                .map(drone -> convertToDroneDto(drone, modelMapper))
                .collect(Collectors.toList());
    }

    @Override
    public Integer checkBatteryCapacity(Long droneId) {
        requireNonNull(droneId, "Drone id cannot be null");
        Optional<Drone> drone = droneRepository.findById(droneId);

        if (!drone.isPresent()) throw new DroneNotFoundException(DRONE_NOT_FOUND);
        return drone.get().getBatteryCapacity();
    }

    @Override
    public MedicationDto checkMedicationForDrone(Long droneId) {
        Optional<Drone> drone = droneRepository.findById(droneId);

        if (!drone.isPresent()) throw new DroneNotFoundException(DRONE_NOT_FOUND);

        return convertToMedicationDto(drone.get().getMedication(), modelMapper);
    }
}
