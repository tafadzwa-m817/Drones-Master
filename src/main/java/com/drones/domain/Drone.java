package com.drones.domain;

import com.drones.util.DroneModel;
import com.drones.util.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "drone")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, updatable = false)
    private Long droneId;
    @NotNull(message = "Serial number cannot be null")
    @Column(length = 100, unique = true)
    private String serialNumber;
    @Enumerated(EnumType.STRING)
    private DroneModel model;
    private Integer weightLimit;
    @Max(100)
    @Min(0)
    private Integer batteryCapacity;
    @Enumerated(EnumType.STRING)
    private State state;
    @ManyToOne
    private Medication medication;
}
