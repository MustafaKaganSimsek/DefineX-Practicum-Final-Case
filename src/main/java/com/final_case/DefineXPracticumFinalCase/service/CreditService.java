package com.final_case.DefineXPracticumFinalCase.service;

import com.final_case.DefineXPracticumFinalCase.dto.ExistCreditRequest;
import com.final_case.DefineXPracticumFinalCase.dto.NewCreditRequest;
import com.final_case.DefineXPracticumFinalCase.model.Credit;
import com.final_case.DefineXPracticumFinalCase.model.Customer;

import java.util.UUID;

public interface CreditService {
    Credit save(Credit creditRequest);

    Credit update(UUID creditId,Credit creditRequest);

    Credit createNewCredit(NewCreditRequest creditRequest);

    Credit getExistCredit(ExistCreditRequest request);
}
