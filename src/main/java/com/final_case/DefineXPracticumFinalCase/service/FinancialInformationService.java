package com.final_case.DefineXPracticumFinalCase.service;

import com.final_case.DefineXPracticumFinalCase.dto.ExistCreditRequest;
import com.final_case.DefineXPracticumFinalCase.dto.NewCreditRequest;
import com.final_case.DefineXPracticumFinalCase.model.FinancialInformation;
import com.final_case.DefineXPracticumFinalCase.model.Customer;

public interface FinancialInformationService {
    FinancialInformation save(FinancialInformation financialInformation);

    FinancialInformation creditApplicationForNewUser(NewCreditRequest creditRequest);

    FinancialInformation creditApplicationorExistCustomer(ExistCreditRequest request);
    }
