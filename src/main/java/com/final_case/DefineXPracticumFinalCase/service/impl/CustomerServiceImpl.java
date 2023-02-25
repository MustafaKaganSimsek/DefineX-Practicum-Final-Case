package com.final_case.DefineXPracticumFinalCase.service.impl;

import com.final_case.DefineXPracticumFinalCase.dto.CustomerPersonalInfoDto;
import com.final_case.DefineXPracticumFinalCase.dto.CustomerFinancialInfoDto;
import com.final_case.DefineXPracticumFinalCase.exception.CustomerNotFoundException;
import com.final_case.DefineXPracticumFinalCase.exception.ExistsCustomerException;
import com.final_case.DefineXPracticumFinalCase.model.Customer;
import com.final_case.DefineXPracticumFinalCase.repository.CustomerRepository;
import com.final_case.DefineXPracticumFinalCase.service.CreditScoreService;
import com.final_case.DefineXPracticumFinalCase.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CreditScoreService creditScoreService;



    @Override
    public Customer save(Customer customerPersonalInfoDto) {
        log.debug("Request to save Customer: {}",customerPersonalInfoDto);
        if (customerRepository.existsByIdentityNumber(customerPersonalInfoDto.getIdentityNumber())){
            throw new ExistsCustomerException("Customer is exists");
        }
        else {
            customerPersonalInfoDto.setCreditScore(creditScoreService.getCreditScore());
            return customerRepository.save(customerPersonalInfoDto);

        }
    }

    @Override
    public Customer updatePersonalInformation(UUID id, CustomerPersonalInfoDto customerPersonalInfoDto) {
        log.debug("Request to update Customer personal information by id: {}", id );
        Customer existCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer " + id + " Not Found"));
        if (customerPersonalInfoDto.getName()!= null){
            existCustomer.setName(customerPersonalInfoDto.getName());
        }
        if (customerPersonalInfoDto.getSurname()!=null){
            existCustomer.setSurname(customerPersonalInfoDto.getSurname());
        }
        if (customerPersonalInfoDto.getCallNumber()!=null){
            existCustomer.setCallNumber(customerPersonalInfoDto.getCallNumber());
        }
        if (customerPersonalInfoDto.getIdentityNumber()!=null){
            existCustomer.setIdentityNumber(customerPersonalInfoDto.getIdentityNumber());
        }
        if (customerPersonalInfoDto.getBirthDay()!=null){
            existCustomer.setBirthDay(customerPersonalInfoDto.getBirthDay());
        }


        return customerRepository.save(existCustomer);
    }

    @Override
    public Customer updateFinancialInformation(UUID id,  double salary, double assurance) {
        Customer existCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer " + id + " Not Found"));

        existCustomer.setSalary(salary);
        existCustomer.setAssurance(assurance);
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
    public Customer findByIdentityNumberAndBirthDay(String identityNumber , LocalDate birthDay) {
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
