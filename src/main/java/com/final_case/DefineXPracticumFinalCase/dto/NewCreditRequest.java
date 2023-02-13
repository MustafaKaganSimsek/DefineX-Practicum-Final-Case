package com.final_case.DefineXPracticumFinalCase.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class NewCreditRequest {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Surname is mandatory")
    private String surname;

    @NotBlank(message = "Identity number is mandatory")
    private String identityNumber;

    @NotNull(message = "Salary is mandatory")
    private double salary;

    @NotBlank(message = "Call number is mandatory")
    private String callNumber;

    @NotNull(message = "Birth Day is mandatory")
    private LocalDate birthDay;

    private double assurance;
}