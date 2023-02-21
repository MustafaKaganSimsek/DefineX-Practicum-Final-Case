package com.final_case.DefineXPracticumFinalCase.controller;

import com.final_case.DefineXPracticumFinalCase.dto.CreditDto;
import com.final_case.DefineXPracticumFinalCase.dto.ExistCreditRequest;
import com.final_case.DefineXPracticumFinalCase.dto.NewCreditRequest;
import com.final_case.DefineXPracticumFinalCase.dto.converter.CreditConverter;
import com.final_case.DefineXPracticumFinalCase.service.CreditApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("credit-application")
public class CreditApplicationController {

    private final CreditConverter converter;
    private final CreditApplicationService creditApplicationService;

    @PostMapping("/new")
    public ResponseEntity<CreditDto> createNewCreditApplication(@Valid @RequestBody NewCreditRequest creditRequest){
        log.debug("REST Request to create new credit application: {}",creditRequest);

        return ResponseEntity.ok(converter.convert(creditApplicationService.createNewCredit(creditRequest)));
    }

    @GetMapping("/exist")
    public ResponseEntity<CreditDto> getExistCreditApplication(@Valid @RequestBody ExistCreditRequest request){
        log.debug("REST Request to get exist credit application: {}",request);

        return ResponseEntity.ok(converter.convert(creditApplicationService.getExistCredit(request)));
    }

    @PostMapping("/update")
    public ResponseEntity<CreditDto> updateExistCreditApplication(@Valid @RequestBody NewCreditRequest creditRequest){
        log.debug("REST Request to update exist credit application: {}",creditRequest);

        return ResponseEntity.ok(converter.convert(creditApplicationService.updateExistCredit(creditRequest)));
    }
}
