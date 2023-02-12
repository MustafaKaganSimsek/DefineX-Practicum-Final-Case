package com.final_case.DefineXPracticumFinalCase.dto.converter;

import com.final_case.DefineXPracticumFinalCase.dto.FinancialInformationDto;
import com.final_case.DefineXPracticumFinalCase.model.FinancialInformation;
import org.springframework.stereotype.Component;

@Component
public class FinancialInformationConverter {

    public FinancialInformationDto convert (FinancialInformation from){
        return FinancialInformationDto.builder()
                .id(from.getId())
                .salary(from.getSalary())
                .creditScore(from.getCreditScore())
                .assurance(from.getAssurance())
                .creditLimit(from.getCreditLimit())
                .isAcceptedCredit(from.isAcceptedCredit())
                .build();
    }

    public FinancialInformation convert (FinancialInformationDto from){
        return FinancialInformation.builder()

                .build();
    }
}
