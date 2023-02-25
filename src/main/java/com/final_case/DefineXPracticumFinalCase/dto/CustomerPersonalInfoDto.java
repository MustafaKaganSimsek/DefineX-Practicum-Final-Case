package com.final_case.DefineXPracticumFinalCase.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Builder
@Data
public class CustomerPersonalInfoDto {
    private String name;
    private String surname;
    private String identityNumber;
    private String callNumber;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthDay;
}
