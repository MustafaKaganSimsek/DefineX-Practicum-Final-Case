package com.final_case.DefineXPracticumFinalCase.service;

import com.final_case.DefineXPracticumFinalCase.dto.ExistCreditRequest;
import com.final_case.DefineXPracticumFinalCase.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    Customer save (Customer customer);

    Customer update(Customer customer);

    void delete(UUID id);

    List<Customer> findAll();

    Customer findByIdentityNumberAndBirthDay(ExistCreditRequest request);
}
