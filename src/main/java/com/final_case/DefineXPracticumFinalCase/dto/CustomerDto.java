package com.final_case.DefineXPracticumFinalCase.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Builder
@Data
@EqualsAndHashCode
public class CustomerDto {
    private UUID id;
    private String name;
    private String surname;
    private String identityNumber;
    private String callNumber;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthDay;
    private double salary;
    private double assurance;
    private double creditScore;
    private UUID creditId;

    private String createdBy;
    private Date createdDate;
    private String lastModifiedBy;
    private Date lastModifiedDate;
}
