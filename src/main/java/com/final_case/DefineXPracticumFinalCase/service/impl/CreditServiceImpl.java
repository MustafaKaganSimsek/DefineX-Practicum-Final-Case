package com.final_case.DefineXPracticumFinalCase.service.impl;

import com.final_case.DefineXPracticumFinalCase.dto.CreateCustomerRequest;
import com.final_case.DefineXPracticumFinalCase.dto.CustomerFinancialInfo;
import com.final_case.DefineXPracticumFinalCase.enumeration.CreditMessage;
import com.final_case.DefineXPracticumFinalCase.exception.CreditNotFoundExeption;
import com.final_case.DefineXPracticumFinalCase.exception.ExistCreditException;
import com.final_case.DefineXPracticumFinalCase.model.Credit;
import com.final_case.DefineXPracticumFinalCase.model.Customer;
import com.final_case.DefineXPracticumFinalCase.repository.CreditRepository;
import com.final_case.DefineXPracticumFinalCase.service.CreditService;
import com.final_case.DefineXPracticumFinalCase.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Log4j2
@RequiredArgsConstructor
@Service
public class CreditServiceImpl implements CreditService {

    private final CustomerService customerService;
    private final CreditRepository creditRepository;


    //
    @Transactional
    @Override
    public Credit update(UUID creditId, CustomerFinancialInfo financialInfo){

        log.debug("Request to update Credit by id: {}",creditId);

        Credit credit = creditRepository.findById(creditId)
                .orElseThrow(()->new CreditNotFoundExeption("Credit " + creditId + " Not Found"));

        Credit updatedCredit = creditInquiry(financialInfo.getSalary(), financialInfo.getAssurance(),financialInfo.getCreditScore());
        credit.setAccepted(updatedCredit.isAccepted());
        credit.setMessage(updatedCredit.getMessage());
        credit.setCreditLimit(updatedCredit.getCreditLimit());

        return creditRepository.save(credit);
    }





    @Transactional
    @Override
    public Credit createCreditForNewCustomer(CreateCustomerRequest customerRequest){
        log.debug("Request to create new Credit : {}",customerRequest.getIdentityNumber());

        Customer customer = customerService.save(customerRequest);

        Credit credit = creditInquiry(customer.getSalary(),customer.getAssurance(),customer.getCreditScore());

        credit.setCustomer(customer);

        credit = creditRepository.save(credit);

        log.debug("MESSAGE ----> call number : "+customer.getCallNumber()
                +" credit limit : " +credit.getCreditLimit()
                +" credit status : " +credit.getMessage() );

        return credit;
    }


    @Transactional
    @Override
    public Credit createCreditForExistCustomer(String identityNumber){
        log.debug("Request to create exist Credit by identity number: {}",identityNumber);

        Customer customer = customerService.findByIdentityNumber(identityNumber);
        if (customer.getCredit()!=null){
            throw new ExistCreditException("Credit exist or customer : "+ identityNumber);
        }
        Credit credit = creditInquiry(customer.getSalary(),customer.getAssurance(),customer.getCreditScore());

        credit.setCustomer(customer);

        credit = creditRepository.save(credit);

        log.debug("MESSAGE ----> call number : "+customer.getCallNumber()
                +" credit limit : " +credit.getCreditLimit()
                +" credit status : " +credit.getMessage() );

        return credit;
    }



    @Transactional
    @Override
    public Credit updateWithCustomer(String identityNumber, CustomerFinancialInfo financialInfo) {
        log.debug("Request to update Credit by identity number: {}",identityNumber);

        Customer existCustomer = customerService.findByIdentityNumber(identityNumber);

        Customer updatedCustomer = customerService.updateFinancialInformation(existCustomer.getId(), financialInfo);

        Credit credit = creditInquiry(updatedCustomer.getSalary(),updatedCustomer.getAssurance(),updatedCustomer.getCreditScore());
        credit.setId(existCustomer.getCredit().getId());
        credit.setCustomer(updatedCustomer);

        credit = creditRepository.save(credit);

        log.debug("MESSAGE ----> call number : "+updatedCustomer.getCallNumber()
                +" credit limit : " +credit.getCreditLimit()
                +" credit status : " +credit.getMessage() );

        return credit;
    }


    @Override
    public Credit getExistCredit(String idetityNumber , Date birthDay) {
        log.debug("Request to get exist Credit : {} and {}",idetityNumber,birthDay);
        Customer customer = customerService.findByIdentityNumberAndBirthDay(idetityNumber,birthDay);

        return customer.getCredit();
    }




    private Credit creditInquiry(double salary, double assurance, double creditScore){
        log.debug("Request to get Credit inquiry");

        final int creditLimitMultiplier = 4;

        if (creditScore>=500
                && creditScore<1000
                && salary<5000) {

            double creditLimit = 10000+(assurance*0.1);
            return createCreditObject(CreditMessage.ACCEPTED,creditLimit,true);

        }else if (creditScore>=500
                && creditScore<1000
                && salary>=5000
                && salary<10000){

            double creditLimit = 20000+(assurance*0.2);
            return createCreditObject(CreditMessage.ACCEPTED,creditLimit,true);

        } else if (creditScore>=500
                && creditScore<1000
                && salary>=10000) {

            double creditLimit = (salary * (creditLimitMultiplier * 0.5)) + (assurance * 0.25);
            return createCreditObject(CreditMessage.ACCEPTED,creditLimit,true);

        } else if (creditScore>=1000) {
            double creditLimit = (salary * creditLimitMultiplier) + (assurance * 0.5);

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
