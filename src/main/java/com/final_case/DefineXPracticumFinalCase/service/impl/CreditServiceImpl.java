package com.final_case.DefineXPracticumFinalCase.service.impl;

import com.final_case.DefineXPracticumFinalCase.dto.ExistCreditRequest;
import com.final_case.DefineXPracticumFinalCase.dto.NewCreditRequest;
import com.final_case.DefineXPracticumFinalCase.enumeration.CreditMessage;
import com.final_case.DefineXPracticumFinalCase.exception.CreditNotFoundExeption;
import com.final_case.DefineXPracticumFinalCase.exception.FinancialInformationNotFoundExeption;
import com.final_case.DefineXPracticumFinalCase.model.Credit;
import com.final_case.DefineXPracticumFinalCase.model.Customer;
import com.final_case.DefineXPracticumFinalCase.model.FinancialInformation;
import com.final_case.DefineXPracticumFinalCase.repository.CreditRepository;
import com.final_case.DefineXPracticumFinalCase.service.CreditService;
import com.final_case.DefineXPracticumFinalCase.service.CustomerService;
import com.final_case.DefineXPracticumFinalCase.service.FinancialInformationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Log4j2
@RequiredArgsConstructor
@Service
public class CreditServiceImpl implements CreditService {

    private final CreditRepository creditRepository;


    @Override
    public Credit save(Credit creditRequest){
        return creditRepository.save(creditRequest);
    }


    @Override
    public Credit update(UUID creditId,Credit creditRequest){
        Credit credit = creditRepository.findById(creditId).orElseThrow(()->new CreditNotFoundExeption("Credit " + creditId + " Not Found"));

        return creditRepository.save(Credit.builder()
                        .id(creditId)
                        .isAccepted(creditRequest.isAccepted())
                        .creditLimit(creditRequest.getCreditLimit())
                        .message(creditRequest.getMessage())
                        .build());
    }
}
