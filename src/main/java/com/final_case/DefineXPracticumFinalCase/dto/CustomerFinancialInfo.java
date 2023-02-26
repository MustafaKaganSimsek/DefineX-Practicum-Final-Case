package com.final_case.DefineXPracticumFinalCase.dto;

import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerFinancialInfo {

    @Min(0)
    private double salary;
    private double assurance;
    private double creditScore;
}
