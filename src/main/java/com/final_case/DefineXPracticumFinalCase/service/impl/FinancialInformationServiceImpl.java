package com.final_case.DefineXPracticumFinalCase.service.impl;

import com.final_case.DefineXPracticumFinalCase.exception.FinancialInformationNotFoundExeption;
import com.final_case.DefineXPracticumFinalCase.exception.SqlException;
import com.final_case.DefineXPracticumFinalCase.model.FinancialInformation;
import com.final_case.DefineXPracticumFinalCase.repository.FinancialInformationRepository;
import com.final_case.DefineXPracticumFinalCase.service.CreditScoreService;
import com.final_case.DefineXPracticumFinalCase.service.FinancialInformationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class FinancialInformationServiceImpl implements FinancialInformationService {
    private final CreditScoreService creditScoreService;
    private final FinancialInformationRepository financialInformationRepository;


    @Override
    public FinancialInformation save(FinancialInformation financialInformationRequest)throws SqlException {
        log.debug("Request to update FinancialInformation : {}", financialInformationRequest);

        return financialInformationRepository.save(FinancialInformation.builder()
                        .salary(financialInformationRequest.getSalary())
                        .creditScore(creditScoreService.getCreditScore())
                        .assurance(financialInformationRequest.getAssurance())
                        .customer(financialInformationRequest.getCustomer())
                .build());
    }



    @Override
    public FinancialInformation update (UUID financialInformationId , FinancialInformation financialInformationRequest){
        FinancialInformation financialInformation =financialInformationRepository.findById(financialInformationId)
                .orElseThrow(() -> new FinancialInformationNotFoundExeption("Financial Information " + financialInformationId + " Not Found"));

        if(financialInformationRequest.getSalary() != financialInformation.getSalary()){
            financialInformation.setSalary(financialInformationRequest.getSalary());
        }
        if (financialInformationRequest.getAssurance() != financialInformation.getAssurance()){
            financialInformation.setAssurance(financialInformationRequest.getAssurance());
        }
        if (financialInformationRequest.getCreditScore() != financialInformation.getCreditScore()){
            financialInformation.setCreditScore(financialInformationRequest.getCreditScore());
        }

        return financialInformationRepository.save(financialInformation);
    }

    @Override
    public void delete(@PathVariable UUID id){
        log.debug("Request to delete Financial Information : {}", id);
        financialInformationRepository.deleteById(id);
    }

    @Override
    public List<FinancialInformation> findAll(){
        log.debug("Request to get all Financial Information");

        return financialInformationRepository.findAll();
    }




}
