package com.final_case.DefineXPracticumFinalCase.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequest {
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Surname is mandatory")
    private String surname;
    @NotBlank(message = "Identity Number is mandatory")
    private String identityNumber;
    @NotBlank(message = "Call Number is mandatory")
    private String callNumber;

    @NotNull(message = "Birthday is mandatory")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDay;

    @Min(0)
    private double salary;
    private double assurance;
}
