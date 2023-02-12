package com.final_case.DefineXPracticumFinalCase.service;

import com.final_case.DefineXPracticumFinalCase.dto.CreditApplicationDto;
import com.final_case.DefineXPracticumFinalCase.dto.ExistCustomerRequest;
import com.final_case.DefineXPracticumFinalCase.model.CreditApplication;
import com.final_case.DefineXPracticumFinalCase.model.Customer;

public interface CreditApplicationService {
    CreditApplicationDto save(CreditApplication creditApplication);

    CreditApplicationDto creditApplicationForNewUser(Customer customer);

    CreditApplication creditApplicationorExistCustomer(ExistCustomerRequest request);
    }
