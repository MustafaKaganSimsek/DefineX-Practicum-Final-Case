package com.final_case.DefineXPracticumFinalCase.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class ExistCreditRequest {
    @NotBlank(message = "Identity number is mandatory")
    private String identityNumber;
    
    @NotNull(message = "Birth Day is mandatory")
    private LocalDate birthDay;
}
