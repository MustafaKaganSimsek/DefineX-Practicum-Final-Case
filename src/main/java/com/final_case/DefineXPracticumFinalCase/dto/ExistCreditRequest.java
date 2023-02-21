package com.final_case.DefineXPracticumFinalCase.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class ExistCreditRequest {
    @NotBlank(message = "Identity number is mandatory")
    private String identityNumber;
    
    @NotNull(message = "Birth Day is mandatory")
    private LocalDate birthDay;
}
