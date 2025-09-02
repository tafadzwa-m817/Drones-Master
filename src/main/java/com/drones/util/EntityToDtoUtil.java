package com.drones.util;

import com.drones.domain.AuditEventLog;
import com.drones.domain.Drone;
import com.drones.domain.Medication;
import com.drones.service.dto.AuditEventLogDto;
import com.drones.service.dto.DroneDto;
import com.drones.service.dto.MedicationDto;
import org.modelmapper.ModelMapper;


public class EntityToDtoUtil {
    public static DroneDto convertToDroneDto(Drone drone, ModelMapper modelMapper){
        DroneDto droneDto = modelMapper.map(drone, DroneDto.class);
        return droneDto;
    }
    public static MedicationDto convertToMedicationDto(Medication medication,
                                                       ModelMapper modelMapper){
        MedicationDto medicationDto = modelMapper.map(medication, MedicationDto.class);
        return medicationDto;
    }
    public static AuditEventLogDto convertToAuditEventLogDto(AuditEventLog auditEventLog,
                                                             ModelMapper modelMapper){
        AuditEventLogDto auditEventLogDto = modelMapper.map(auditEventLog, AuditEventLogDto.class);
        return auditEventLogDto;
    }
}
