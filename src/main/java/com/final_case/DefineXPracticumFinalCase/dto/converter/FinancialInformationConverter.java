package com.final_case.DefineXPracticumFinalCase.dto.converter;

import com.final_case.DefineXPracticumFinalCase.dto.CustomerDto;
import com.final_case.DefineXPracticumFinalCase.dto.FinancialInformationDto;
import com.final_case.DefineXPracticumFinalCase.model.Customer;
import com.final_case.DefineXPracticumFinalCase.model.FinancialInformation;
import org.springframework.stereotype.Component;

@Component
public class FinancialInformationConverter {

    public FinancialInformationDto convert (FinancialInformation from){
        FinancialInformationDto dto =FinancialInformationDto.builder()
                .id(from.getId())
                .salary(from.getSalary())
                .creditScore(from.getCreditScore())
                .assurance(from.getAssurance())
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

    public FinancialInformation convert (FinancialInformationDto from){
        return FinancialInformation.builder()
                .id(from.getId())
                .salary(from.getSalary())
                .creditScore(from.getCreditScore())
                .assurance(from.getAssurance())
                .customer(Customer.builder()
                        .id(from.getCustomerId())
                        .build())
                .build();
    }
}
