package com.final_case.DefineXPracticumFinalCase.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPersonalInfo {
    private String name;
    private String surname;
    private String identityNumber;
    private String callNumber;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthDay;
}
