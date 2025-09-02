package com.drones.service.event_log;

import com.drones.domain.AuditEventLog;
import com.drones.errors.AuditEventLogNotFoundException;
import com.drones.persistence.AuditEventLogRepository;
import com.drones.service.dto.AuditEventLogDto;
import com.drones.util.AppConstants;
import com.drones.util.EntityToDtoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.drones.util.AppConstants.AUDIT_EVENT_LOG_NOT_FOUND;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuditEventLogServiceImpl implements AuditEventLogService{
    private final AuditEventLogRepository auditEventLogRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<AuditEventLogDto> filterByDate(LocalDate date, Pageable pageable) {
        List<AuditEventLogDto> auditEventLogs = auditEventLogRepository
                .findAllByCreatedDate(date, pageable)
                .stream().map(auditEventLog -> EntityToDtoUtil
                        .convertToAuditEventLogDto(auditEventLog, modelMapper))
                .collect(Collectors.toList());
        if (auditEventLogs.isEmpty() && Objects.equals(auditEventLogs, null)){
            throw new AuditEventLogNotFoundException(AUDIT_EVENT_LOG_NOT_FOUND);
        }
        return new PageImpl<>(auditEventLogs, pageable, auditEventLogs.size());
    }
}
