package com.drones.api;

import com.drones.service.DroneRequest;
import com.drones.service.DroneService;
import com.drones.service.dto.DroneDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("v1/drone")
@RequiredArgsConstructor
public class DroneRestController {
    private final DroneService droneService;

    @Operation(summary = "Register Drone")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Drone registered",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DroneDto.class)) })})
    @PostMapping("/register")
    public ResponseEntity<DroneDto> registerDrone(@RequestBody DroneRequest droneRequest){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(droneService.registerDrone(droneRequest));
    }
    @Operation(summary = "Find Drone By Drone Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Drone found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DroneDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Drone not found",
                    content = @Content) })
    @GetMapping("/id/{droneId}")
    public ResponseEntity<DroneDto> findDroneById(@PathVariable Long droneId){
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(droneService.findDroneById(droneId));
    }
    @Operation(summary = "Load Drone With Medication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Drone loaded",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DroneDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Drone not loaded",
                    content = @Content) })
    @GetMapping("/load/{medicationCode}")
    public ResponseEntity<DroneDto> loadDroneWithMedication(@PathVariable  String medicationCode){
        return ResponseEntity.status(HttpStatus.OK)
                .body(droneService.loadDroneWithMedication(medicationCode));
    }
    @Operation(summary = "Find All Available Drones For Loading")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Drones found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DroneDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Drones not found",
                    content = @Content) })
    @GetMapping("all/available")
    public ResponseEntity<List<DroneDto>> checkAvailableDronesForLoading(){
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(droneService.checkAvailableDronesForLoading());
    }
}
