package com.final_case.DefineXPracticumFinalCase.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Builder
@Data
public class CustomerPersonalInfoDto {
    private String name;
    private String surname;
    private String identityNumber;
    private String callNumber;
    private LocalDate birthDay;
}
