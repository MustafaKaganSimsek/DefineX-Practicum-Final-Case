package com.final_case.DefineXPracticumFinalCase.controller;

import com.final_case.DefineXPracticumFinalCase.dto.CreditDto;
import com.final_case.DefineXPracticumFinalCase.dto.ExistCreditRequest;
import com.final_case.DefineXPracticumFinalCase.dto.FinancialInformationDto;
import com.final_case.DefineXPracticumFinalCase.dto.NewCreditRequest;
import com.final_case.DefineXPracticumFinalCase.dto.converter.CreditConverter;
import com.final_case.DefineXPracticumFinalCase.model.Credit;
import com.final_case.DefineXPracticumFinalCase.service.CreditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("credit")
public class CreditController {

    private final CreditService creditService;
    private final CreditConverter converter;

    @PostMapping("/new")
    public ResponseEntity<CreditDto> createNewCreditApplication(@Valid @RequestBody NewCreditRequest creditRequest){
        log.debug("REST Request to create new credit application: {}",creditRequest);

        return ResponseEntity.ok(converter.convert(creditService.createNewCredit(creditRequest)));
    }

    @GetMapping("/exist")
    public ResponseEntity<CreditDto> getExistCreditApplication(@Valid @RequestBody ExistCreditRequest request){
        log.debug("REST Request to get exist credit application: {}",request);

        return ResponseEntity.ok(converter.convert(creditService.getExistCredit(request)));
    }


}
