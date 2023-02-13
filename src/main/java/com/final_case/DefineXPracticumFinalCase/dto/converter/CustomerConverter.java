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
        return CustomerDto.builder()
                .id(from.getId())
                .identityNumber(from.getIdentityNumber())
                .name(from.getName())
                .surname(from.getSurname())
                .birthDay(from.getBirthDay())
                .callNumber(from.getCallNumber())
                .financialInformationId(from.getFinancialInformation().getId())
                .build();
    }
    public Customer convert (CustomerDto from){
        return Customer.builder()
                .id(from.getId())
                .identityNumber(from.getIdentityNumber())
                .name(from.getName())
                .surname(from.getSurname())
                .birthDay(from.getBirthDay())
                .callNumber(from.getCallNumber())
                .financialInformation(FinancialInformation.builder()
                        .id(from.getFinancialInformationId())
                        .build())
                .build();
    }



    public List<CustomerDto> convert(List<Customer> listFrom){
        return listFrom.stream()
                .map(from ->convert(from))
                .toList();
    }

}
