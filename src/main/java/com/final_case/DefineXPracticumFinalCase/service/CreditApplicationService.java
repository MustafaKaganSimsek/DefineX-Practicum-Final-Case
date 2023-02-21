package com.final_case.DefineXPracticumFinalCase.service;

import com.final_case.DefineXPracticumFinalCase.dto.ExistCreditRequest;
import com.final_case.DefineXPracticumFinalCase.dto.NewCreditRequest;
import com.final_case.DefineXPracticumFinalCase.model.Credit;

public interface CreditApplicationService {

    Credit createNewCredit(NewCreditRequest creditRequest);

    Credit updateExistCredit(NewCreditRequest creditRequest);

    Credit getExistCredit(ExistCreditRequest request);

}
