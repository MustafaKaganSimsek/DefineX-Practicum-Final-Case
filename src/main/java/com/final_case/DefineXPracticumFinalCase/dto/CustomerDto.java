package com.final_case.DefineXPracticumFinalCase.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
public class CustomerDto {
    private UUID id;
    private String name;
    private String surname;
    private String identityNumber;
    private double salary;
    private String callNumber;
    private LocalDate birthDay;
    private double assurance;
    private double creditScore;
    private UUID financialInformationId;
}
