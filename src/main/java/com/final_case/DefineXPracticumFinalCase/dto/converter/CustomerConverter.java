package com.final_case.DefineXPracticumFinalCase.dto.converter;

import com.final_case.DefineXPracticumFinalCase.dto.CustomerDto;
import com.final_case.DefineXPracticumFinalCase.model.Customer;
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
                .CreditApplicationId(from.getFinancialInformation().getId())
                .build();
    }
    public List<CustomerDto> convert(List<Customer> listFrom){
        return listFrom.stream()
                .map(from ->convert(from))
                .toList();
    }
}
