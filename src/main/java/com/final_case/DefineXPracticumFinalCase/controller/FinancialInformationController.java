package com.final_case.DefineXPracticumFinalCase.controller;

import com.final_case.DefineXPracticumFinalCase.dto.FinancialInformationDto;
import com.final_case.DefineXPracticumFinalCase.dto.ExistCreditRequest;
import com.final_case.DefineXPracticumFinalCase.dto.NewCreditRequest;
import com.final_case.DefineXPracticumFinalCase.dto.converter.FinancialInformationConverter;
import com.final_case.DefineXPracticumFinalCase.model.Customer;
import com.final_case.DefineXPracticumFinalCase.service.FinancialInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor

@RestController
@RequestMapping("creditapp/credit_application")
public class FinancialInformationController {

    private final FinancialInformationService financialInformationService;
    private final FinancialInformationConverter converter;

    @PostMapping("/new_customer")
    public ResponseEntity<FinancialInformationDto> creditApplicationForNewCustomer(@Valid @RequestBody NewCreditRequest newCreditRequest){
        return ResponseEntity.ok(converter
                .convert(financialInformationService.creditApplicationForNewUser(newCreditRequest)));
    }

    @GetMapping("/exist_customer")
    public ResponseEntity<FinancialInformationDto> creditApplicationorExistCustomer(@RequestBody ExistCreditRequest request){
        return ResponseEntity.ok(converter
                .convert(financialInformationService.creditApplicationorExistCustomer(request)));
    }
}
