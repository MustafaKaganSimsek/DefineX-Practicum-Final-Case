package com.final_case.DefineXPracticumFinalCase.service.impl;

import com.final_case.DefineXPracticumFinalCase.dto.ExistCreditRequest;
import com.final_case.DefineXPracticumFinalCase.dto.NewCreditRequest;
import com.final_case.DefineXPracticumFinalCase.enumeration.CreditMessage;
import com.final_case.DefineXPracticumFinalCase.exception.CreditNotFoundExeption;
import com.final_case.DefineXPracticumFinalCase.exception.FinancialInformationNotFoundExeption;
import com.final_case.DefineXPracticumFinalCase.model.Credit;
import com.final_case.DefineXPracticumFinalCase.model.Customer;
import com.final_case.DefineXPracticumFinalCase.model.FinancialInformation;
import com.final_case.DefineXPracticumFinalCase.service.CreditApplicationService;
import com.final_case.DefineXPracticumFinalCase.service.CreditService;
import com.final_case.DefineXPracticumFinalCase.service.CustomerService;
import com.final_case.DefineXPracticumFinalCase.service.FinancialInformationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Log4j2
@Service
public class CreditApplicationServiceImpl implements CreditApplicationService {

    private final CustomerService customerService;
    private final FinancialInformationService financialInformationService;
    private final CreditService creditService;

    private final int creditLimitMultiplier = 4;

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


        return creditService.save(Credit.builder()
                .message(credit.getMessage())
                .creditLimit(credit.getCreditLimit())
                .isAccepted(credit.isAccepted())
                .customer(customer)
                .build());
    }

    @Override
    public Credit updateExistCredit(NewCreditRequest creditRequest) {
        Customer existCustomer = customerService.findByIdentityNumber(creditRequest.getIdentityNumber());

        customerService.update(existCustomer.getId(),Customer.builder()
                        .name(creditRequest.getName())
                        .surname(creditRequest.getSurname())
                        .identityNumber(creditRequest.getIdentityNumber())
                        .callNumber(creditRequest.getCallNumber())
                        .birthDay(creditRequest.getBirthDay())
                .build());

        FinancialInformation financialInformation = existCustomer.getFinancialInformation();

        if (financialInformation == null){

            financialInformation = financialInformationService.save(FinancialInformation.builder()
                            .salary(creditRequest.getSalary())
                            .assurance(creditRequest.getAssurance())
                            .customer(existCustomer)
                    .build());


        }else {
            financialInformation = financialInformationService.update(existCustomer.getFinancialInformation().getId(),FinancialInformation.builder()
                            .salary(creditRequest.getSalary())
                            .assurance(creditRequest.getAssurance())
                            .build());
        }

        Credit credit = getCreditInquiry(financialInformation);


        if (existCustomer.getCredit() == null){

            return creditService.save(Credit.builder()
                    .message(credit.getMessage())
                    .creditLimit(credit.getCreditLimit())
                    .isAccepted(credit.isAccepted())
                    .customer(existCustomer)
                    .build());


        }else {
            return creditService.update(existCustomer.getCredit().getId(),Credit.builder()
                    .message(credit.getMessage())
                    .creditLimit(credit.getCreditLimit())
                    .isAccepted(credit.isAccepted())
                    .build());
        }

    }


    @Override
    public Credit getExistCredit(ExistCreditRequest request) {
        log.debug("Request to get exist credit application: {}",request);
        Customer customer = customerService.findByIdentityNumberAndBirthDay(request);

        if (customer.getCredit()==null){
            throw new CreditNotFoundExeption("Credit Not Found For Customer:  "+request);
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
