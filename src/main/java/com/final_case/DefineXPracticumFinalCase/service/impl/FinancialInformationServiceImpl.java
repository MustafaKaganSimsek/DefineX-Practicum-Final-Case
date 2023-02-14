package com.final_case.DefineXPracticumFinalCase.service.impl;

import com.final_case.DefineXPracticumFinalCase.dto.converter.FinancialInformationConverter;
import com.final_case.DefineXPracticumFinalCase.exception.FinancialInformationNotFoundExeption;
import com.final_case.DefineXPracticumFinalCase.model.FinancialInformation;
import com.final_case.DefineXPracticumFinalCase.repository.FinancialInformationRepository;
import com.final_case.DefineXPracticumFinalCase.service.CreditScoreService;
import com.final_case.DefineXPracticumFinalCase.service.CreditService;
import com.final_case.DefineXPracticumFinalCase.service.FinancialInformationService;
import com.final_case.DefineXPracticumFinalCase.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class FinancialInformationServiceImpl implements FinancialInformationService {
    private final CreditScoreService creditScoreService;
    private final FinancialInformationRepository financialInformationRepository;

    private final int creditLimitMultiplier = 4;

    @Override
    public FinancialInformation save(FinancialInformation financialInformationRequest) {
        log.debug("Request to update FinancialInformation : {}", financialInformationRequest);

        return financialInformationRepository.save(FinancialInformation.builder()
                        .salary(financialInformationRequest.getSalary())
                        .creditScore(creditScoreService.getCreditScore())
                        .assurance(financialInformationRequest.getAssurance())
                        .customer(financialInformationRequest.getCustomer())
                .build());
    }



    @Override
    public FinancialInformation update (UUID financialInformationId ,FinancialInformation financialInformationRequest){
        Optional<FinancialInformation> financialInformation = Optional.ofNullable(financialInformationRepository.findById(financialInformationId)
                .orElseThrow(() -> new FinancialInformationNotFoundExeption("Financial Information " + financialInformationId + " Not Found")));

        financialInformation.ifPresent(financialInfo -> {
            financialInfo.setSalary(financialInformationRequest.getSalary());
            financialInfo.setAssurance(financialInformationRequest.getAssurance());
            financialInfo.setCreditScore(financialInformationRequest.getCreditScore());
            financialInformationRepository.save(financialInfo);
        } );
        return financialInformation.get();
    }




}
