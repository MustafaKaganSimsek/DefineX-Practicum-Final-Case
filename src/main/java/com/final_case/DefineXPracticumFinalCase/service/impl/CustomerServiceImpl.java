package com.final_case.DefineXPracticumFinalCase.service.impl;

import com.final_case.DefineXPracticumFinalCase.dto.ExistCreditRequest;
import com.final_case.DefineXPracticumFinalCase.exception.CustomerNotFoundException;
import com.final_case.DefineXPracticumFinalCase.model.Customer;
import com.final_case.DefineXPracticumFinalCase.repository.CustomerRepository;
import com.final_case.DefineXPracticumFinalCase.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;



    @Override
    public Customer save(Customer customerRequest) {
        log.debug("Request to save Customer: {}",customerRequest);
        return customerRepository.save(customerRequest);
    }

    @Override
    public Customer update(UUID id,Customer customerRequest) {
        log.debug("Request to update Customer : {}", customerRequest);
        Customer existCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer " + id + " Not Found"));
        if (customerRequest.getName()!= existCustomer.getName()){
            existCustomer.setName(customerRequest.getName());
        }
        if (customerRequest.getSurname()!=existCustomer.getSurname()){
            existCustomer.setSurname(customerRequest.getSurname());
        }
        if (customerRequest.getCallNumber()!=existCustomer.getCallNumber()){
            existCustomer.setCallNumber(customerRequest.getCallNumber());
        }
        if (customerRequest.getIdentityNumber()!=existCustomer.getIdentityNumber()){
            existCustomer.setIdentityNumber(customerRequest.getIdentityNumber());
        }

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
    public Customer findByIdentityNumberAndBirthDay(ExistCreditRequest request) {
        log.debug("Request to get Customer by Identity Number and Birth Day: {} ", request);

        return customerRepository.findByIdentityNumberAndBirthDay(request.getIdentityNumber(), request.getBirthDay())
                .orElseThrow(()-> new CustomerNotFoundException("Customer "+ request +" Not Found"));
    }
}
