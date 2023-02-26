package com.final_case.DefineXPracticumFinalCase.service;

import com.final_case.DefineXPracticumFinalCase.dto.CreateCustomerRequest;
import com.final_case.DefineXPracticumFinalCase.dto.CustomerFinancialInfo;
import com.final_case.DefineXPracticumFinalCase.model.Credit;
import com.final_case.DefineXPracticumFinalCase.model.Customer;
import jakarta.transaction.Transactional;

import java.util.Date;
import java.util.UUID;

public interface CreditService {

    Credit update(UUID creditId,CustomerFinancialInfo financialInfoDto);

    @Transactional
    Credit createCreditForNewCustomer(CreateCustomerRequest customerRequest);

    Credit createCreditForExistCustomer(String identityNumber);

    @Transactional
    Credit updateWithCustomer(String identityNumber, CustomerFinancialInfo financialInfoDto);

    Credit getExistCredit(String idetityNumber , Date birthDay);
}
