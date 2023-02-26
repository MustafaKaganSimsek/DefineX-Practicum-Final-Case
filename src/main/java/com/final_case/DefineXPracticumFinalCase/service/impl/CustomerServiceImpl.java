package com.final_case.DefineXPracticumFinalCase.service.impl;

import com.final_case.DefineXPracticumFinalCase.dto.CreateCustomerRequest;
import com.final_case.DefineXPracticumFinalCase.dto.CustomerFinancialInfo;
import com.final_case.DefineXPracticumFinalCase.dto.CustomerPersonalInfo;
import com.final_case.DefineXPracticumFinalCase.exception.CustomerNotFoundException;
import com.final_case.DefineXPracticumFinalCase.exception.ExistsCustomerException;
import com.final_case.DefineXPracticumFinalCase.model.Customer;
import com.final_case.DefineXPracticumFinalCase.repository.CustomerRepository;
import com.final_case.DefineXPracticumFinalCase.service.CreditScoreService;
import com.final_case.DefineXPracticumFinalCase.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CreditScoreService creditScoreService;



    @Override
    public Customer save(CreateCustomerRequest customerRequest) {
        log.debug("Request to save Customer: {}",customerRequest);
        if (customerRepository.existsByIdentityNumber(customerRequest.getIdentityNumber())){
            throw new ExistsCustomerException("Customer is exists");
        }
        else {
            Customer customer = Customer.builder()
                    .name(customerRequest.getName())
                    .surname(customerRequest.getSurname())
                    .identityNumber(customerRequest.getIdentityNumber())
                    .birthDay(customerRequest.getBirthDay())
                    .callNumber(customerRequest.getCallNumber())
                    .salary(customerRequest.getSalary())
                    .assurance(customerRequest.getAssurance())
                    .creditScore(creditScoreService.getCreditScore())
                    .credit(null)
                    .build();
            return customerRepository.save(customer);

        }
    }

    @Override
    public Customer updatePersonalInformation(UUID id, CustomerPersonalInfo customerPersonalInfo) {
        log.debug("Request to update Customer personal information by id: {}", id );
        Customer existCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer " + id + " Not Found"));
        if (customerPersonalInfo.getName()!= null){
            existCustomer.setName(customerPersonalInfo.getName());
        }
        if (customerPersonalInfo.getSurname()!=null){
            existCustomer.setSurname(customerPersonalInfo.getSurname());
        }
        if (customerPersonalInfo.getCallNumber()!=null){
            existCustomer.setCallNumber(customerPersonalInfo.getCallNumber());
        }
        if (customerPersonalInfo.getIdentityNumber()!=null){
            existCustomer.setIdentityNumber(customerPersonalInfo.getIdentityNumber());
        }
        if (customerPersonalInfo.getBirthDay()!=null){
            existCustomer.setBirthDay(customerPersonalInfo.getBirthDay());
        }


        return customerRepository.save(existCustomer);
    }

    @Override
    public Customer updateFinancialInformation(UUID id, CustomerFinancialInfo financialInfo) {
        Customer existCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer " + id + " Not Found"));

        existCustomer.setSalary(financialInfo.getSalary());
        existCustomer.setAssurance(financialInfo.getAssurance());
        existCustomer.setCreditScore(creditScoreService.getCreditScore());
        return customerRepository.save(existCustomer);
    }

    @Override
    public void delete(UUID id) {
        log.debug("Request to delete Customer : {}", id);
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> findAll() {
        log.debug("Request to get all Customer");
        return customerRepository.findAll();
    }


    @Override
    public Customer findByIdentityNumberAndBirthDay(String identityNumber , Date birthDay) {
        log.debug("Request to get Customer by Identity Number and Birth Day: {} and {}",identityNumber,birthDay);

        return customerRepository.findByIdentityNumberAndBirthDay(identityNumber, birthDay)
                .orElseThrow(()-> new CustomerNotFoundException("Customer Not Found By " + identityNumber + " And " + birthDay ));
    }


    @Override
    public Customer findByIdentityNumber(String identityNumber){
        log.debug("Request to get Customer by Identity Number : {} ",identityNumber);
        return customerRepository.findByIdentityNumber(identityNumber)
                .orElseThrow(()-> new CustomerNotFoundException("Customer "+ identityNumber +" Not Found"));

    }
}
