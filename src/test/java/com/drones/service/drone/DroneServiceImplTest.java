package com.drones.service.drone;

import com.drones.domain.Drone;
import com.drones.domain.Medication;
import com.drones.errors.AboveMaxWeightLimitException;
import com.drones.errors.InvalidInputParameterException;
import com.drones.persistence.DroneRepository;
import com.drones.persistence.MedicationRepository;
import com.drones.service.DroneRequest;
import com.drones.service.DroneService;
import com.drones.service.DroneServiceImpl;
import com.drones.util.AppConstants;
import com.drones.util.DroneModel;
import com.drones.util.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static com.drones.util.AppConstants.ABOVE_MAX_WEIGHT_LIMIT;
import static com.drones.util.AppConstants.IN_VALID_CODE;
import static com.drones.util.DroneModel.Cruiserweight;
import static com.drones.util.State.IDLE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * @author Romeo Jerenyama
 * @created 17/01/2023 - 23:27
 */
@ExtendWith(MockitoExtension.class)
class DroneServiceImplTest {

    @Mock
    private DroneRepository droneRepository;
    @Mock
    private MedicationRepository medicationRepository;
    @Mock
    private ModelMapper modelMapper;
    private Drone drone;
    private DroneRequest droneRequest;
    private Medication medication;
    private DroneService underTest;
    @BeforeEach
    void setUp() {
        underTest = new DroneServiceImpl(modelMapper,droneRepository, medicationRepository);

        medication = Medication.builder()
                .id(1l).code("A5")
                .name("Paracetamol").image("Image")
                .weight(52).build();

        drone = Drone.builder().droneId(1l)
                .state(IDLE).model(Cruiserweight)
                .serialNumber("4OHK").batteryCapacity(52)
                .weightLimit(64).medication(medication)
                .build();

    }
    @Test
    @DisplayName("Given valid DroneRequest registerDrone method should register Drone")
    void givenValidRequest_ShouldRegisterDrone() {
        //GIVEN
        DroneRequest droneRequest = new DroneRequest();
        droneRequest.setSerialNumber("123456");
        droneRequest.setState(State.LOADING);
        droneRequest.setModel(Cruiserweight);
        droneRequest.setWeightLimit(12);
        droneRequest.setBatteryCapacity(45);

        //WHEN
        underTest.registerDrone(droneRequest);

        //THEN
        Drone expectedDrone = Drone.builder()
                .serialNumber(droneRequest.getSerialNumber())
                .model(droneRequest.getModel())
                .state(droneRequest.getState())
                .batteryCapacity(droneRequest.getBatteryCapacity())
                .weightLimit(droneRequest.getWeightLimit())
                .build();

        ArgumentCaptor<Drone> droneArgumentCaptor =
                ArgumentCaptor.forClass(Drone.class);
        verify(droneRepository).save(droneArgumentCaptor.capture());

        Drone capturedDrone = droneArgumentCaptor.getValue();

        assertThat(capturedDrone).isEqualTo(expectedDrone);

    }
    @Test
    @DisplayName("Throws AboveMaxWeightLimitException if passed weight above weight limit")
    void givenWeightAboveMaxLimitShouldThrowException(){
        //GIVEN
        int aboveWeightLimit = 501;
        DroneRequest droneRequest = new DroneRequest();
        droneRequest.setSerialNumber("123456");
        droneRequest.setState(State.LOADING);
        droneRequest.setModel(Cruiserweight);
        droneRequest.setWeightLimit(aboveWeightLimit);
        droneRequest.setBatteryCapacity(45);

        //WHEN
        //THEN
        assertThatThrownBy(() -> underTest.registerDrone(droneRequest))
                .isInstanceOf(AboveMaxWeightLimitException.class)
                .hasMessageContaining(ABOVE_MAX_WEIGHT_LIMIT, droneRequest.getWeightLimit());

    }
    @Test
    void givenValidDroneIdShouldCheckBatteryCapacity(){
        //GIVEN
        Long validId = 1L;
        given(droneRepository.findById(validId))
                .willReturn(Optional.ofNullable(drone));

        //WHEN
        underTest.findDroneById(validId);

    }
}