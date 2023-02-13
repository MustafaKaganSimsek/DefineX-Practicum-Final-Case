package com.final_case.DefineXPracticumFinalCase.controller;

import com.final_case.DefineXPracticumFinalCase.dto.FinancialInformationDto;
import com.final_case.DefineXPracticumFinalCase.dto.ExistCreditRequest;
import com.final_case.DefineXPracticumFinalCase.dto.NewCreditRequest;
import com.final_case.DefineXPracticumFinalCase.dto.converter.FinancialInformationConverter;
import com.final_case.DefineXPracticumFinalCase.model.Customer;
import com.final_case.DefineXPracticumFinalCase.service.FinancialInformationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping("creditapp/credit_application")
public class FinancialInformationController {

    private final FinancialInformationService financialInformationService;
    private final FinancialInformationConverter converter;

    @PostMapping("/new_customer")
    public ResponseEntity<FinancialInformationDto> createNewCreditApplication(@Valid @RequestBody NewCreditRequest creditRequest){
        log.debug("REST Request to create new credit application: {}",creditRequest);

        return ResponseEntity.ok(converter
                .convert(financialInformationService.createNewCreditApplication(creditRequest)));
    }

    @GetMapping("/exist_customer")
    public ResponseEntity<FinancialInformationDto> getExistCreditApplication(@Valid @RequestBody ExistCreditRequest request){
        log.debug("REST Request to get exist credit application: {}",request);

        return ResponseEntity.ok(converter
                .convert(financialInformationService.getExistCreditApplication(request)));
    }
}
