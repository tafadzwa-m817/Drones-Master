package com.drones.api;

import com.drones.service.dto.AuditEventLogDto;
import com.drones.service.event_log.AuditEventLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("v1/log")
@RequiredArgsConstructor
public class AuditEventLogRestController {
    private final AuditEventLogService auditEventLogService;

    @Operation(summary = "Filter All Logs By Date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "AuditEventLog found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuditEventLogDto.class)) }),
            @ApiResponse(responseCode = "404", description = "AuditEventLog not found",
                    content = @Content) })
    @GetMapping
    public ResponseEntity<Page<AuditEventLogDto>> filterLogsByDate(@RequestParam("date")
                                                                       @DateTimeFormat(pattern = "dd/MM/yyyy")
                                                                   LocalDate date,
                                                                   @PageableDefault Pageable pageable){
        return ResponseEntity.status(OK)
                .body(auditEventLogService.filterByDate(date, pageable));
    }
}
