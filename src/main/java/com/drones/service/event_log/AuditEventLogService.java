package com.drones.service.event_log;

import com.drones.domain.AuditEventLog;
import com.drones.service.dto.AuditEventLogDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;


public interface AuditEventLogService {
    Page<AuditEventLogDto> filterByDate(LocalDate date, Pageable pageable);
}
