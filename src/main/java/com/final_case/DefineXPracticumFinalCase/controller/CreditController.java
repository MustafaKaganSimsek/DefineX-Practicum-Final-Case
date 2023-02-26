package com.final_case.DefineXPracticumFinalCase.controller;

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


@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("credit")
public class CreditController {

    private final CreditService creditService;
    private final CreditConverter converter;




    @PostMapping("/update/{id}")
    public ResponseEntity<CreditDto> update(@PathVariable UUID creditId, @Valid CustomerFinancialInfo financialInfo) {
        log.debug("REST request to update Credit by id: {}",creditId);

        return ResponseEntity.ok(converter.convert(creditService.update(creditId,financialInfo)));
    }

    @PostMapping("/create")
    public ResponseEntity<CreditDto> createCreditForNewCustomer(@Valid @RequestBody Customer customerRequest){
        log.debug("REST Request to create new credit application: {}",customerRequest.getIdentityNumber());

        return ResponseEntity.ok(converter.convert(creditService.createCreditForNewCustomer(customerRequest)));
    }
    @PostMapping("/create/{identityNumber}")
    public ResponseEntity<CreditDto> createCreditForExistCustomer(@PathVariable String identityNumber){
        log.debug("REST Request to create new credit application: {}",identityNumber);

        return ResponseEntity.ok(converter.convert(creditService.createCreditForExistCustomer(identityNumber)));
    }

    @GetMapping("/customer")
    public ResponseEntity<CreditDto> getExistCredit(@RequestParam String identityNumber,
                                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthDay){
        log.debug("REST Request to get exist credit application: {} and {}",identityNumber,birthDay);

        return ResponseEntity.ok(converter.convert(creditService.getExistCredit(identityNumber,birthDay)));
    }

    @PostMapping("/update_with_customer/{identityNumber}")
    public ResponseEntity<CreditDto> updateWithCustomer(@PathVariable String identityNumber, @RequestBody @Valid CustomerFinancialInfo financialInfo){
        log.debug("REST Request to update exist credit application: {}",identityNumber);

        return ResponseEntity.ok(converter.convert(creditService.updateWithCustomer(identityNumber,financialInfo)));
    }

}
