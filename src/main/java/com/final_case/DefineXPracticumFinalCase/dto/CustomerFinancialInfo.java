package com.final_case.DefineXPracticumFinalCase.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerFinancialInfo {

    private double salary;
    private double assurance;
    private double creditScore;
}
