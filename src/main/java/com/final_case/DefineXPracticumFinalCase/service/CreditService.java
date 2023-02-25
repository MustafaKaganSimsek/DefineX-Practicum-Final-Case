package com.final_case.DefineXPracticumFinalCase.service;

import com.final_case.DefineXPracticumFinalCase.dto.CustomerFinancialInfoDto;
import com.final_case.DefineXPracticumFinalCase.model.Credit;
import com.final_case.DefineXPracticumFinalCase.model.Customer;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.UUID;

public interface CreditService {
    Credit save(Credit creditRequest);

    Credit update(UUID creditId,Credit creditRequest);

    void delete(UUID creditId);

    @Transactional
    Credit createCreditForNewCustomer(Customer customerRequest);

    Credit createCreditForExistCustomer(String identityNumber);

    @Transactional
    Credit updateExistCredit(String identityNumber, double salary, double assurance);

    Credit getExistCredit(String idetityNumber , LocalDate birthDay);
}
