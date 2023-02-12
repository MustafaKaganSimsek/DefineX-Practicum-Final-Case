package com.final_case.DefineXPracticumFinalCase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class CreditApplicationDto {
    private UUID id;
    private double creditLimit;
    private boolean isAccepted;
}
