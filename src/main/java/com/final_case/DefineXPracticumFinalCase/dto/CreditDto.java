package com.final_case.DefineXPracticumFinalCase.dto;

import com.final_case.DefineXPracticumFinalCase.enumeration.CreditMessage;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.UUID;

@Builder
@Data
@EqualsAndHashCode
public class CreditDto {

    private UUID id;

    private double creditLimit;
    private boolean isAccepted;
    private CreditMessage message;
    private UUID customerId;

    private String createdBy;
    private Date createdDate;
    private String lastModifiedBy;
    private Date lastModifiedDate;

}
