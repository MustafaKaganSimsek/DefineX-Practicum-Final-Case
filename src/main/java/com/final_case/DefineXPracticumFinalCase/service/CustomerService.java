package com.final_case.DefineXPracticumFinalCase.service;

import com.final_case.DefineXPracticumFinalCase.dto.CustomerFinancialInfo;
import com.final_case.DefineXPracticumFinalCase.dto.CustomerPersonalInfo;
import com.final_case.DefineXPracticumFinalCase.model.Customer;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface CustomerService {

    Customer save (Customer customer);

    Customer updatePersonalInformation(UUID id , CustomerPersonalInfo customerPersonalInfo);

    Customer updateFinancialInformation(UUID id , CustomerFinancialInfo financialInfo);

    void delete(UUID id);

    List<Customer> findAll();

    Customer findByIdentityNumberAndBirthDay(String idetityNumber , Date birthDay);

    Customer findByIdentityNumber(String identityNumber);
}
