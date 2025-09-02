package com.drones.domain;

import com.drones.util.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Entity
@Builder
@Table(name = "event_log")
@NoArgsConstructor
@AllArgsConstructor
public class AuditEventLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long droneId;
    private String serialNumber;
    private Integer batteryCapacity;
    @Enumerated(EnumType.STRING)
    private State state;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDate createdDate;
}
