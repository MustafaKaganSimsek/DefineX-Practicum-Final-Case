package com.final_case.DefineXPracticumFinalCase.dto.converter;

import com.final_case.DefineXPracticumFinalCase.dto.CreditDto;
import com.final_case.DefineXPracticumFinalCase.dto.CustomerDto;
import com.final_case.DefineXPracticumFinalCase.model.Credit;
import com.final_case.DefineXPracticumFinalCase.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CreditConverter {

    public CreditDto convert (Credit from){
        CreditDto dto = CreditDto.builder()
                .id(from.getId())
                .message(from.getMessage())
                .creditLimit(from.getCreditLimit())
                .isAccepted(from.isAccepted())
                .createdBy(from.getCreatedBy())
                .createdDate(from.getCreatedDate())
                .lastModifiedBy(from.getLastModifiedBy())
                .lastModifiedDate(from.getLastModifiedDate())
                .build();
        if (from.getCustomer() != null){
            dto.setCustomerId(from.getCustomer().getId());
        }

        return dto;
    }
}
