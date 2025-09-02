package com.drones.scheduler;

import com.drones.domain.AuditEventLog;
import com.drones.persistence.AuditEventLogRepository;
import com.drones.persistence.DroneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Component
@RequiredArgsConstructor
@Slf4j
public class DroneScheduler {
    private final DroneRepository droneRepository;
    private final AuditEventLogRepository auditEventLogRepository;

    @Scheduled(fixedDelay = 300000)
    public void checkDroneBattery(){
        log.info("Inside cron job........");
        droneRepository.findAll().stream().forEach(drone -> {
            AuditEventLog auditEventLog = new AuditEventLog();
            log.info("Checking battery capacity for drone with , id = {}, serialNumber = {}",
                    drone.getDroneId(), drone.getSerialNumber());
            auditEventLog.setDroneId(drone.getDroneId());
            auditEventLog.setState(drone.getState());
            auditEventLog.setSerialNumber(drone.getSerialNumber());
            auditEventLog.setBatteryCapacity(drone.getBatteryCapacity());
            auditEventLog.setCreatedDate(LocalDate.now());
            auditEventLogRepository.save(auditEventLog);
        });
    }
}
