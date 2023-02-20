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
    private final CustomerService customerService;
    private final FinancialInformationService financialInformationService;
    private final CreditRepository creditRepository;

    private final int creditLimitMultiplier = 4;

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



    @Override
    public Credit createNewCredit(NewCreditRequest creditRequest){
        log.debug("Request to create new credit application: {}",creditRequest);
        Customer customer = customerService.save(Customer.builder()
                .name(creditRequest.getName())
                .surname(creditRequest.getSurname())
                .identityNumber(creditRequest.getIdentityNumber())
                .callNumber(creditRequest.getCallNumber())
                .birthDay(creditRequest.getBirthDay())
                .build());

        FinancialInformation financialInformation = financialInformationService.save(FinancialInformation.builder()
                        .salary(creditRequest.getSalary())
                        .assurance(creditRequest.getAssurance())
                        .customer(customer)
                .build());
        Credit credit = getCreditInquiry(financialInformation);


        return save(Credit.builder()
                .message(credit.getMessage())
                .creditLimit(credit.getCreditLimit())
                .isAccepted(credit.isAccepted())
                .customer(customer)
                .build());
    }


    @Override
    public Credit getExistCredit(ExistCreditRequest request) {
        log.debug("Request to get exist credit application: {}",request);
        Customer customer = customerService.findByIdentityNumberAndBirthDay(request);
        if (customer.getFinancialInformation()==null){
            throw new FinancialInformationNotFoundExeption("Financial Information Not Found For Customer: " + request);
        }
        if (customer.getCredit()==null){
            Credit credit = getCreditInquiry(customer.getFinancialInformation());
            creditRepository.save(Credit.builder()
                            .creditLimit(credit.getCreditLimit())
                            .message(credit.getMessage())
                            .isAccepted(credit.isAccepted())
                            .customer(customer)
                    .build());
        }
        if (customer.getFinancialInformation().getLastModifiedDate().after(customer.getCredit().getLastModifiedDate())){
            return update(customer.getCredit().getId(),getCreditInquiry(customer.getFinancialInformation()));
        }
        return customer.getCredit();
    }


    private Credit getCreditInquiry(FinancialInformation financialInformation){
        log.debug("Request to get credit inquiry");

        if (financialInformation.getCreditScore()>=500
                && financialInformation.getCreditScore()<1000
                && financialInformation.getSalary()<5000) {

            double creditLimit = 10000;
            return createCreditObject(CreditMessage.ACCEPTED,creditLimit,true);

        }else if (financialInformation.getCreditScore()>=500
                && financialInformation.getCreditScore()<1000
                && financialInformation.getSalary()>=5000
                && financialInformation.getSalary()<10000){

            double creditLimit = 20000+(financialInformation.getAssurance()*0.2);
            return createCreditObject(CreditMessage.ACCEPTED,creditLimit,true);

        } else if (financialInformation.getCreditScore()>=500
                && financialInformation.getCreditScore()<1000
                && financialInformation.getSalary()>10000) {

            double creditLimit = (financialInformation.getSalary() * (creditLimitMultiplier / 2)) + (financialInformation.getAssurance() * 0.25);
            return createCreditObject(CreditMessage.ACCEPTED,creditLimit,true);

        } else if (financialInformation.getCreditScore()>=1000) {
            double creditLimit = (financialInformation.getSalary() * creditLimitMultiplier) + (financialInformation.getAssurance() * 0.5);

            return createCreditObject(CreditMessage.ACCEPTED,creditLimit,true);

        }else {
            //if credit score is less than 500
            return createCreditObject(CreditMessage.DENIED,0,false);
        }

    }
    private Credit createCreditObject(CreditMessage message, double creditLimit, boolean isAccepted){
        return Credit.builder()
                .message(message)
                .creditLimit(creditLimit)
                .isAccepted(isAccepted)
                .build();
    }
}
