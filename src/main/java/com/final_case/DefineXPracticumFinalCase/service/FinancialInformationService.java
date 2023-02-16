package com.final_case.DefineXPracticumFinalCase.service;

import com.final_case.DefineXPracticumFinalCase.dto.ExistCreditRequest;
import com.final_case.DefineXPracticumFinalCase.dto.NewCreditRequest;
import com.final_case.DefineXPracticumFinalCase.model.FinancialInformation;
import com.final_case.DefineXPracticumFinalCase.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

public interface FinancialInformationService {
    FinancialInformation save(FinancialInformation financialInformation);

    FinancialInformation update (UUID financialInformationId , FinancialInformation financialInformationRequest);

    void delete(UUID id);

    List<FinancialInformation> findAll();
}
