package com.final_case.DefineXPracticumFinalCase.service;

import com.final_case.DefineXPracticumFinalCase.dto.CustomerPersonalInfoDto;
import com.final_case.DefineXPracticumFinalCase.dto.CustomerFinancialInfoDto;
import com.final_case.DefineXPracticumFinalCase.model.Customer;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface CustomerService {

    Customer save (Customer customer);

    Customer updatePersonalInformation(UUID id , CustomerPersonalInfoDto customerPersonalInfoDto);

    Customer updateFinancialInformation(UUID id ,double salary, double assurance);

    void delete(UUID id);

    List<Customer> findAll();

    Customer findByIdentityNumberAndBirthDay(String idetityNumber , LocalDate birthDay);

    Customer findByIdentityNumber(String identityNumber);
}
