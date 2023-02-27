package com.final_case.DefineXPracticumFinalCase.controller;

import com.final_case.DefineXPracticumFinalCase.dto.CreateCustomerRequest;
import com.final_case.DefineXPracticumFinalCase.dto.CreditDto;
import com.final_case.DefineXPracticumFinalCase.dto.CustomerFinancialInfo;
import com.final_case.DefineXPracticumFinalCase.dto.converter.CreditConverter;
import com.final_case.DefineXPracticumFinalCase.model.Credit;
import com.final_case.DefineXPracticumFinalCase.model.Customer;
import com.final_case.DefineXPracticumFinalCase.service.CreditService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

//!!!This class is written on the administrator page or in special cases that need to be returned to the customer. Optional.My goal is customer return.
@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("credit")
public class CreditController {

    private final CreditService creditService;
    private final CreditConverter converter;

    @PostMapping("/create")
    public ResponseEntity<CreditDto> createCreditForNewCustomer(
            @Valid @RequestBody CreateCustomerRequest customerRequest) {
        log.debug("REST Request to create new credit application: {}", customerRequest.getIdentityNumber());

        return ResponseEntity.ok(converter.convert(creditService.createCreditForNewCustomer(customerRequest)));
    }

    @PostMapping("/update/{identityNumber}")
    public ResponseEntity<CreditDto> updateWithCustomer(@PathVariable String identityNumber,
            @RequestBody @Valid CustomerFinancialInfo financialInfo) {
        log.debug("REST Request to update exist credit application: {}", identityNumber);

        return ResponseEntity.ok(converter.convert(creditService.updateWithCustomer(identityNumber, financialInfo)));
    }

    @GetMapping("/customer")
    public ResponseEntity<CreditDto> getExistCredit(@RequestParam String identityNumber,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthDay) {
        log.debug("REST Request to get exist credit application: {} and {}", identityNumber, birthDay);

        return ResponseEntity.ok(converter.convert(creditService.getExistCredit(identityNumber, birthDay)));
    }

}
