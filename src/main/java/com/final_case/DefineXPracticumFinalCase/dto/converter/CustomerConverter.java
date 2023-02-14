package com.final_case.DefineXPracticumFinalCase.dto.converter;

import com.final_case.DefineXPracticumFinalCase.dto.CustomerDto;
import com.final_case.DefineXPracticumFinalCase.dto.FinancialInformationDto;
import com.final_case.DefineXPracticumFinalCase.model.Customer;
import com.final_case.DefineXPracticumFinalCase.model.FinancialInformation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerConverter {

    public CustomerDto convert (Customer from){
        CustomerDto dto = CustomerDto.builder()
                .id(from.getId())
                .identityNumber(from.getIdentityNumber())
                .name(from.getName())
                .surname(from.getSurname())
                .birthDay(from.getBirthDay())
                .callNumber(from.getCallNumber())
                .createdBy(from.getCreatedBy())
                .createdDate(from.getCreatedDate())
                .lastModifiedBy(from.getLastModifiedBy())
                .lastModifiedDate(from.getLastModifiedDate())
                .build();
        if (from.getFinancialInformation() != null){
            dto.setFinancialInformationId(from.getFinancialInformation().getId());
        }
        if (from.getCredit() != null){
            dto.setCreditId(from.getCredit().getId());
        }
        return dto;
    }
    public Customer convert (CustomerDto from){
        return Customer.builder()
                .id(from.getId())
                .identityNumber(from.getIdentityNumber())
                .name(from.getName())
                .surname(from.getSurname())
                .birthDay(from.getBirthDay())
                .callNumber(from.getCallNumber())
                .build();
    }



    public List<CustomerDto> convert(List<Customer> listFrom){
        return listFrom.stream()
                .map(from ->convert(from))
                .toList();
    }

}
