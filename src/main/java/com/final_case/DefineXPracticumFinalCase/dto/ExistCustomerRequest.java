package com.final_case.DefineXPracticumFinalCase.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExistCustomerRequest {
    private String identityNumber;
    private LocalDate birthDay;

}
