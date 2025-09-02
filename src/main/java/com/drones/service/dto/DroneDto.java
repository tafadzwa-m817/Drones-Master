package com.drones.service.dto;

import com.drones.util.DroneModel;
import com.drones.util.State;
import lombok.Data;


@Data
public class DroneDto {
    private Long droneId;
    private String serialNumber;
    private DroneModel model;
    private Integer weightLimit;
    private Integer batteryCapacity;
    private State state;
}
