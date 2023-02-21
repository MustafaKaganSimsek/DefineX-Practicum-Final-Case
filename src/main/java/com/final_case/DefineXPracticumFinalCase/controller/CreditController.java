package com.final_case.DefineXPracticumFinalCase.controller;

import com.final_case.DefineXPracticumFinalCase.dto.CreditDto;
import com.final_case.DefineXPracticumFinalCase.dto.ExistCreditRequest;
import com.final_case.DefineXPracticumFinalCase.dto.FinancialInformationDto;
import com.final_case.DefineXPracticumFinalCase.dto.NewCreditRequest;
import com.final_case.DefineXPracticumFinalCase.dto.converter.CreditConverter;
import com.final_case.DefineXPracticumFinalCase.model.Credit;
import com.final_case.DefineXPracticumFinalCase.service.CreditService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("credit")
public class CreditController {

    private final CreditService creditService;
    private final CreditConverter converter;



}
