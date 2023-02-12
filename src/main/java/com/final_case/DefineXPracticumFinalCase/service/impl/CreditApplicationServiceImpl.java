package com.final_case.DefineXPracticumFinalCase.service.impl;

import com.final_case.DefineXPracticumFinalCase.dto.CreditApplicationDto;
import com.final_case.DefineXPracticumFinalCase.dto.CustomerDto;
import com.final_case.DefineXPracticumFinalCase.dto.ExistCustomerRequest;
import com.final_case.DefineXPracticumFinalCase.dto.converter.CreditApplicationConverter;
import com.final_case.DefineXPracticumFinalCase.model.CreditApplication;
import com.final_case.DefineXPracticumFinalCase.model.Customer;
import com.final_case.DefineXPracticumFinalCase.repository.CreditApplicationRepository;
import com.final_case.DefineXPracticumFinalCase.service.CreditApplicationService;
import com.final_case.DefineXPracticumFinalCase.service.CreditScoreService;
import com.final_case.DefineXPracticumFinalCase.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditApplicationServiceImpl implements CreditApplicationService {
    private final CustomerService customerService;
    private final CreditApplicationConverter converter;
    private final CreditApplicationRepository creditApplicationRepository;

    private final int creditLimitMultiplier = 4;

    @Override
    public CreditApplicationDto save(CreditApplication creditApplication) {

        return converter.convert(creditApplicationRepository.save(creditApplication));
    }

    @Override
    public CreditApplicationDto creditApplicationForNewUser(Customer customer){
        Customer customerSaved = customerService.save(customer);
        CreditApplicationDto creditApplicationDto= getCreditInquiry(customerSaved);
        CreditApplication creditApplication = CreditApplication.builder()
                .creditLimit(creditApplicationDto.getCreditLimit())
                .isAccepted(creditApplicationDto.isAccepted())
                .customer(customerSaved)
                .build();
        return save(creditApplication);
    }

    @Override
    public CreditApplication creditApplicationorExistCustomer(ExistCustomerRequest request) {
        Customer customer = customerService.findByIdentityNumberAndBirthDay(request);
        return customer.getCreditApplication();
    }


    private CreditApplicationDto getCreditInquiry(Customer customer){


        if (customer.getCreditScore()>=500 && customer.getCreditScore()<1000 && customer.getSalary()<5000) {
            return CreditApplicationDto.builder()
                    .creditLimit(10000)
                    .isAccepted(true)
                    .build();
        }else if (customer.getCreditScore()>=500 && customer.getCreditScore()<1000 && 5000<=customer.getSalary() && customer.getSalary()<10000){
            double creditLimit = 20000+(customer.getAssurance()*0.2);

            return CreditApplicationDto.builder()
                    .creditLimit(creditLimit)
                    .isAccepted(true)
                    .build();
        } else if (customer.getCreditScore()>=500 && customer.getCreditScore()<1000 && customer.getSalary()>10000) {
            double creditLimit = (customer.getSalary() * (creditLimitMultiplier / 2)) + (customer.getAssurance() * 0.25);

            return CreditApplicationDto.builder()
                    .creditLimit(creditLimit)
                    .isAccepted(true)
                    .build();
        } else if (customer.getCreditScore()>=1000) {
            double creditLimit = (customer.getSalary() * creditLimitMultiplier) + (customer.getAssurance() * 0.5);

            return CreditApplicationDto.builder()
                    .creditLimit(creditLimit)
                    .isAccepted(true)
                    .build();
        }else {
            //if credit score is less than 500
            return CreditApplicationDto.builder()
                    .creditLimit(0)
                    .isAccepted(false)
                    .build();
        }

    }

}
