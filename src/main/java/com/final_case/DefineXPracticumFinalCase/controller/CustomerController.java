package com.final_case.DefineXPracticumFinalCase.controller;

import com.final_case.DefineXPracticumFinalCase.dto.CustomerDto;
import com.final_case.DefineXPracticumFinalCase.dto.converter.CustomerConverter;
import com.final_case.DefineXPracticumFinalCase.model.Customer;
import com.final_case.DefineXPracticumFinalCase.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("creditapp/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerConverter converter;

    @PostMapping("/save")
    public ResponseEntity<CustomerDto> save(@RequestBody Customer customer){
        return ResponseEntity.ok(converter.convert(customerService.save(customer)));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerDto>> findAll(){
        return ResponseEntity.ok(converter.convert(customerService.findAll()));
    }



}
