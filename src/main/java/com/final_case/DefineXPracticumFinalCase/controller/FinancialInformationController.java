package com.final_case.DefineXPracticumFinalCase.controller;

import com.final_case.DefineXPracticumFinalCase.dto.FinancialInformationDto;
import com.final_case.DefineXPracticumFinalCase.dto.ExistCreditRequest;
import com.final_case.DefineXPracticumFinalCase.dto.NewCreditRequest;
import com.final_case.DefineXPracticumFinalCase.dto.converter.FinancialInformationConverter;
import com.final_case.DefineXPracticumFinalCase.model.Customer;
import com.final_case.DefineXPracticumFinalCase.model.FinancialInformation;
import com.final_case.DefineXPracticumFinalCase.service.FinancialInformationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping("financial-inormation")
public class FinancialInformationController {

    private final FinancialInformationService financialInformationService;
    private final FinancialInformationConverter converter;

    @PostMapping("/save")

    public ResponseEntity<FinancialInformationDto> saveFinancialInformation(@RequestBody FinancialInformation financialInformationRequest) {
        log.debug("REST Request to update FinancialInformation : {}", financialInformationRequest);

        return new ResponseEntity<>(converter.convert(financialInformationService.save(financialInformationRequest)), HttpStatus.CREATED);
    }


    @PostMapping("/update/{id}")
    public ResponseEntity<FinancialInformationDto> updateFinancialInformation (@PathVariable UUID id , @RequestBody FinancialInformation financialInformationRequest){
        log.debug("REST Request to update Financial Information : {}", financialInformationRequest);


        return new ResponseEntity<>(converter.convert(financialInformationService.update(id,financialInformationRequest)),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFinancialInformation(@PathVariable UUID id){
        log.debug("REST Request to delete Financial Information : {}", id);
        financialInformationService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FinancialInformationDto>>findAllFinancialInformation(){
        log.debug("REST Request to get all Financial Information");
        return new ResponseEntity<>(converter.convert(financialInformationService.findAll()),HttpStatus.OK);
    }
}
