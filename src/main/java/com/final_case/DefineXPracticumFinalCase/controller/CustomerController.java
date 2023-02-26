package com.final_case.DefineXPracticumFinalCase.controller;

import com.final_case.DefineXPracticumFinalCase.dto.CustomerDto;
import com.final_case.DefineXPracticumFinalCase.dto.CustomerFinancialInfo;
import com.final_case.DefineXPracticumFinalCase.dto.CustomerPersonalInfo;
import com.final_case.DefineXPracticumFinalCase.dto.converter.CustomerConverter;
import com.final_case.DefineXPracticumFinalCase.model.Credit;
import com.final_case.DefineXPracticumFinalCase.model.Customer;
import com.final_case.DefineXPracticumFinalCase.service.CreditService;
import com.final_case.DefineXPracticumFinalCase.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping("customer")
@Validated
public class CustomerController {
    private final CustomerService customerService;
    private final CreditService creditService;
    private final CustomerConverter converter;

    @PostMapping("/save")
    public ResponseEntity<CustomerDto> save(@Valid @RequestBody Customer customerRequest){
        log.debug("REST Request to save Customer: {}",customerRequest.getIdentityNumber());
        Customer customer = customerService.save(customerRequest);
        Credit credit = creditService.createCreditForExistCustomer(customer.getIdentityNumber());
        customer.setCredit(credit);
        return ResponseEntity.ok(converter.convert(customer));
    }


    @GetMapping("/all")
    public ResponseEntity<List<CustomerDto>> findAll(){
        log.debug("REST Request to get all Customer");
        return ResponseEntity.ok(converter.convert(customerService.findAll()));
    }

    @PostMapping("/update/financial_info/{id}")
    public ResponseEntity<CustomerDto> updateFinancialInfo (@PathVariable(name = "id") UUID id, @RequestBody CustomerFinancialInfo financialInfo){
        log.debug("REST Request to update Customer personal information by id: {}", id);
        Customer customer = customerService.updateFinancialInformation(id, financialInfo);
        CustomerFinancialInfo info = CustomerFinancialInfo.builder()
                .salary(customer.getSalary())
                .assurance(customer.getAssurance())
                .creditScore(customer.getCreditScore())
                .build();

        creditService.update(customer.getCredit().getId(),info);

        return ResponseEntity.ok(converter.convert(customer));

    }

    @PostMapping("/update/personal_info/{id}")
    public ResponseEntity<CustomerDto> updatePersonalInfo (@PathVariable(name = "id") UUID id, @RequestBody CustomerPersonalInfo CustomerPersonalInfo){
        log.debug("REST Request to update Customer personal information by id: {}", id);
        return ResponseEntity.ok(converter.convert(customerService.updatePersonalInformation(id, CustomerPersonalInfo)));

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        log.debug("REST Request to delete Customer : {}", id);
        customerService.delete(id);
    }




}
