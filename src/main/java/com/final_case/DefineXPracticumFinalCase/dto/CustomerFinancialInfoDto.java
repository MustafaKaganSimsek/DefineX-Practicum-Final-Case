package com.final_case.DefineXPracticumFinalCase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class CustomerFinancialInfoDto {
    private UUID id;
    private double salary;
    private double assurance;
    private double creditScore;
}
