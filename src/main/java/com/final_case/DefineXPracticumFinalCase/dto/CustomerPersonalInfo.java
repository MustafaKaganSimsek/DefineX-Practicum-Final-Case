package com.final_case.DefineXPracticumFinalCase.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
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
