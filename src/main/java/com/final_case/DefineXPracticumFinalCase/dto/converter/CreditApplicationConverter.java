package com.final_case.DefineXPracticumFinalCase.dto.converter;

import com.final_case.DefineXPracticumFinalCase.dto.CreditApplicationDto;
import com.final_case.DefineXPracticumFinalCase.model.CreditApplication;
import org.springframework.stereotype.Component;

@Component
public class CreditApplicationConverter {

    public CreditApplicationDto convert (CreditApplication from){
        return CreditApplicationDto.builder()
                .id(from.getId())
                .creditLimit(from.getCreditLimit())
                .isAccepted(from.isAccepted())
                .build();
    }

    public CreditApplication convert (CreditApplicationDto from){
        return CreditApplication.builder()

                .build();
    }
}
