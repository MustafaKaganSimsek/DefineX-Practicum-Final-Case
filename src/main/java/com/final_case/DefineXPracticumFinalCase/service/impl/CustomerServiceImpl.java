package com.final_case.DefineXPracticumFinalCase.service.impl;

import com.final_case.DefineXPracticumFinalCase.dto.ExistCreditRequest;
import com.final_case.DefineXPracticumFinalCase.model.Customer;
import com.final_case.DefineXPracticumFinalCase.repository.CustomerRepository;
import com.final_case.DefineXPracticumFinalCase.service.CreditScoreService;
import com.final_case.DefineXPracticumFinalCase.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CreditScoreService creditScoreService;



    @Override
    public Customer save(Customer customer) {
        Customer customerBuild=Customer.builder()
                .name(customer.getName())
                .surname(customer.getSurname())
                .identityNumber(customer.getIdentityNumber())
                .birthDay(customer.getBirthDay())
                .callNumber(customer.getCallNumber())
                .build();

        return customerRepository.save(customerBuild);
    }

    @Override
    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void delete(UUID id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findByIdentityNumberAndBirthDay(ExistCreditRequest request) {
        return customerRepository.findByIdentityNumberAndBirthDay(request.getIdentityNumber(), request.getBirthDay());
    }
}
