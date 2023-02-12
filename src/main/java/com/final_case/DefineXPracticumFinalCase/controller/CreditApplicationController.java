package com.final_case.DefineXPracticumFinalCase.controller;

import com.final_case.DefineXPracticumFinalCase.dto.CreditApplicationDto;
import com.final_case.DefineXPracticumFinalCase.dto.ExistCustomerRequest;
import com.final_case.DefineXPracticumFinalCase.dto.converter.CreditApplicationConverter;
import com.final_case.DefineXPracticumFinalCase.model.Customer;
import com.final_case.DefineXPracticumFinalCase.service.CreditApplicationService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor

@RestController
@RequestMapping("creditapp/credit_application")
public class CreditApplicationController {

    private final CreditApplicationService creditApplicationService;
    private final CreditApplicationConverter converter;

    @PostMapping("/new_customer")
    public ResponseEntity<CreditApplicationDto> creditApplicationForNewCustomer(@RequestBody Customer customer){
        return ResponseEntity.ok(creditApplicationService.creditApplicationForNewUser(customer));
    }

    @GetMapping("/exist_customer")
    public ResponseEntity<CreditApplicationDto> creditApplicationorExistCustomer(@RequestBody ExistCustomerRequest request){
        return ResponseEntity.ok(converter
                .convert(creditApplicationService
                        .creditApplicationorExistCustomer(request)));
    }
}
