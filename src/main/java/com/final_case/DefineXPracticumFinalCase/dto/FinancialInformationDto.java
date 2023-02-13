package com.final_case.DefineXPracticumFinalCase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class FinancialInformationDto {
    private UUID id;
    private double salary;
    private double assurance;
    private double creditScore;
    private double creditLimit;
    private boolean isAcceptedCredit;
    private UUID customerId;
}
