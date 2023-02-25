package com.final_case.DefineXPracticumFinalCase.service.impl;

import com.final_case.DefineXPracticumFinalCase.dto.CustomerFinancialInfoDto;
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

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Log4j2
@RequiredArgsConstructor
@Service
public class CreditServiceImpl implements CreditService {

    private final CustomerService customerService;
    private final CreditRepository creditRepository;

    @Transactional
    @Override
    public Credit save(Credit creditRequest){
        log.debug("Request to save Credit by Customer: {}",creditRequest.getCustomer().getId());

        if (creditRepository.existsByCustomer(creditRequest.getCustomer())){
            throw new ExistCreditException("Credit exist" + creditRequest.getCustomer().getIdentityNumber());
        }
        return creditRepository.save(creditRequest);
    }


    @Transactional
    @Override
    public Credit update(UUID creditId,Credit creditRequest){

        log.debug("Request to update Credit by id: {}",creditId);

        Credit credit = creditRepository.findById(creditId)
                .orElseThrow(()->new CreditNotFoundExeption("Credit " + creditId + " Not Found"));

        credit.setAccepted(creditRequest.isAccepted());
        credit.setMessage(creditRequest.getMessage());
        credit.setCreditLimit(creditRequest.getCreditLimit());

        return creditRepository.save(credit);
    }


    @Override
    public void delete(UUID creditId){
        log.debug("Request to delete Credit by id: {}",creditId);
        creditRepository.findById(creditId)
                .orElseThrow(()-> new CreditNotFoundExeption("Credit " + creditId + " Not Found"));
        creditRepository.deleteById(creditId);
    }


    @Transactional
    @Override
    public Credit createCreditForNewCustomer(Customer customerRequest){
        log.debug("Request to create new Credit : {}",customerRequest.getIdentityNumber());

        Customer customer = customerService.save(Customer.builder()
                        .name(customerRequest.getName())
                        .surname(customerRequest.getSurname())
                        .identityNumber(customerRequest.getIdentityNumber())
                        .callNumber(customerRequest.getCallNumber())
                        .birthDay(customerRequest.getBirthDay())
                        .salary(customerRequest.getSalary())
                        .assurance(customerRequest.getAssurance())
                    .build());

        Credit credit = getCreditInquiry(customer.getSalary(),customer.getAssurance(),customer.getCreditScore());

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
        Credit credit = getCreditInquiry(customer.getSalary(),customer.getAssurance(),customer.getCreditScore());

        credit.setCustomer(customer);

        credit = creditRepository.save(credit);

        log.debug("MESSAGE ----> call number : "+customer.getCallNumber()
                +" credit limit : " +credit.getCreditLimit()
                +" credit status : " +credit.getMessage() );

        return credit;
    }



    @Transactional
    @Override
    public Credit updateExistCredit(String identityNumber, double salary, double assurance) {
        log.debug("Request to update Credit by identity number: {}",identityNumber);

        Customer existCustomer = customerService.findByIdentityNumber(identityNumber);
        if (existCustomer.getCredit() == null){
            throw new CreditNotFoundExeption("Credit not found for Customer : " + identityNumber);
        }
        Customer updatedCustomer = customerService.updateFinancialInformation(existCustomer.getId(), salary,assurance);

        Credit credit = getCreditInquiry(updatedCustomer.getSalary(),updatedCustomer.getAssurance(),updatedCustomer.getCreditScore());
        credit.setId(existCustomer.getCredit().getId());
        credit.setCustomer(updatedCustomer);

        creditRepository.save(credit);

        log.debug("MESSAGE ----> call number : "+updatedCustomer.getCallNumber()
                +" credit limit : " +credit.getCreditLimit()
                +" credit status : " +credit.getMessage() );

        return credit;
    }


    @Override
    public Credit getExistCredit(String idetityNumber , Date birthDay) {
        log.debug("Request to get exist Credit : {} and {}",idetityNumber,birthDay);
        Customer customer = customerService.findByIdentityNumberAndBirthDay(idetityNumber,birthDay);

        if (customer.getCredit()==null){
            throw new CreditNotFoundExeption("Credit Not Found For Customer : "+idetityNumber+" And "+birthDay);
        }

        return customer.getCredit();
    }




    private Credit getCreditInquiry(double salary,double assurance,double creditScore){
        log.debug("Request to get Credit inquiry");

        final int creditLimitMultiplier = 4;

        if (creditScore>=500
                && creditScore<1000
                && salary<5000) {

            double creditLimit = 10000;
            return createCreditObject(CreditMessage.ACCEPTED,creditLimit,true);

        }else if (creditScore>=500
                && creditScore<1000
                && salary>=5000
                && salary<10000){

            double creditLimit = 20000+(assurance*0.2);
            return createCreditObject(CreditMessage.ACCEPTED,creditLimit,true);

        } else if (creditScore>=500
                && creditScore<1000
                && salary>10000) {

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
