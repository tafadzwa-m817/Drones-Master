package com.drones.service.dto;

import com.drones.util.State;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
public class AuditEventLogDto {
    private Long id;
    private Long droneId;
    private String serialNumber;
    private Integer batteryCapacity;
    private State state;
    private LocalDate createdDate;
}
