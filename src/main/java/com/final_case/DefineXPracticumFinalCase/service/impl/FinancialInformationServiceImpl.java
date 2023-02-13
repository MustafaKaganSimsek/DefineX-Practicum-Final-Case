package com.final_case.DefineXPracticumFinalCase.service.impl;

import com.final_case.DefineXPracticumFinalCase.dto.FinancialInformationDto;
import com.final_case.DefineXPracticumFinalCase.dto.ExistCreditRequest;
import com.final_case.DefineXPracticumFinalCase.dto.NewCreditRequest;
import com.final_case.DefineXPracticumFinalCase.dto.converter.FinancialInformationConverter;
import com.final_case.DefineXPracticumFinalCase.model.FinancialInformation;
import com.final_case.DefineXPracticumFinalCase.model.Customer;
import com.final_case.DefineXPracticumFinalCase.repository.FinancialInformationRepository;
import com.final_case.DefineXPracticumFinalCase.service.CreditScoreService;
import com.final_case.DefineXPracticumFinalCase.service.FinancialInformationService;
import com.final_case.DefineXPracticumFinalCase.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class FinancialInformationServiceImpl implements FinancialInformationService {
    private final CustomerService customerService;
    private final CreditScoreService creditScoreService;
    private final FinancialInformationConverter converter;
    private final FinancialInformationRepository financialInformationRepository;

    private final int creditLimitMultiplier = 4;

    @Override
    public FinancialInformation save(FinancialInformation financialInformation) {
        log.debug("Request to update FinancialInformation : {}", financialInformation);
        return financialInformationRepository.save(financialInformation);
    }

    @Override
    public FinancialInformation createNewCreditApplication(NewCreditRequest creditRequest){
        log.debug("Request to create new credit application: {}",creditRequest);
        Customer customer = customerService.save(Customer.builder()
                        .name(creditRequest.getName())
                        .surname(creditRequest.getSurname())
                        .identityNumber(creditRequest.getIdentityNumber())
                        .callNumber(creditRequest.getCallNumber())
                        .birthDay(creditRequest.getBirthDay())
                .build());

        double creditScore = creditScoreService.getCreditScore();
        FinancialInformationDto financialInformationDto = getCreditInquiry(creditRequest.getSalary(),
                creditScore,
                creditRequest.getAssurance());

        return save(FinancialInformation.builder()
                .salary(creditRequest.getSalary())
                .creditScore(creditScore)
                .assurance(creditRequest.getAssurance())
                .creditLimit(financialInformationDto.getCreditLimit())
                .isAcceptedCredit(financialInformationDto.isAcceptedCredit())
                .customer(customer)
                .build());
    }

    @Override
    public FinancialInformation getExistCreditApplication(ExistCreditRequest request) {
        log.debug("Request to get exist credit application: {}",request);
        Customer customer = customerService.findByIdentityNumberAndBirthDay(request);
        return customer.getFinancialInformation();
    }


    private FinancialInformationDto getCreditInquiry(double salary, double creditScore, double assurance){
        log.debug("Request to get credit inquiry");

        if (creditScore>=500 && creditScore<1000 && salary<5000) {
            return FinancialInformationDto.builder()
                    .creditLimit(10000)
                    .isAcceptedCredit(true)
                    .build();
        }else if (creditScore>=500 && creditScore<1000 && 5000<=salary && salary<10000){
            double creditLimit = 20000+(assurance*0.2);

            return FinancialInformationDto.builder()
                    .creditLimit(creditLimit)
                    .isAcceptedCredit(true)
                    .build();
        } else if (creditScore>=500 && creditScore<1000 && salary>10000) {
            double creditLimit = (salary * (creditLimitMultiplier / 2)) + (assurance * 0.25);

            return FinancialInformationDto.builder()
                    .creditLimit(creditLimit)
                    .isAcceptedCredit(true)
                    .build();
        } else if (creditScore>=1000) {
            double creditLimit = (salary * creditLimitMultiplier) + (assurance * 0.5);

            return FinancialInformationDto.builder()
                    .creditLimit(creditLimit)
                    .isAcceptedCredit(true)
                    .build();
        }else {
            //if credit score is less than 500
            return FinancialInformationDto.builder()
                    .creditLimit(0)
                    .isAcceptedCredit(false)
                    .build();
        }

    }

}
