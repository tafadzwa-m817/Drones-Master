package com.drones.service.medication;

import lombok.Data;

import javax.validation.constraints.Pattern;


@Data
public class MedicationRequest {
    private String name;
    private Integer weight;
    private String code;
    private String image;

}
