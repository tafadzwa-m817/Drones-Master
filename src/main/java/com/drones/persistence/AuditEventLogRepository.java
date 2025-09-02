package com.drones.persistence;

import com.drones.domain.AuditEventLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;


public interface AuditEventLogRepository extends JpaRepository<AuditEventLog, Long> {
    Page<AuditEventLog> findAllByCreatedDate(LocalDate date, Pageable pageable);
}
