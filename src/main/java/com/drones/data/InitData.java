package com.drones.data;

import com.drones.domain.Drone;
import com.drones.domain.Medication;
import com.drones.persistence.DroneRepository;
import com.drones.persistence.MedicationRepository;
import com.drones.util.DroneModel;
import com.drones.util.State;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Data
@Slf4j
@Component
@RequiredArgsConstructor
public class InitData implements CommandLineRunner {
    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;
    @Override
    public void run(String... args){
        log.info("Loading initial drone data");
        Drone drone = Drone.builder()
                .state(State.IDLE)
                .model(DroneModel.Cruiserweight)
                .serialNumber("D-12548")
                .batteryCapacity(100)
                .weightLimit(450)
                .build();
        droneRepository.save(drone);

        log.info("Loading initial medication data");
        Medication medication = Medication.builder()
                .name("Adco-Dol")
                .code("AD-450")
                .image("Default image")
                .weight(250)
                .build();
        medicationRepository.save(medication);
    }
}
