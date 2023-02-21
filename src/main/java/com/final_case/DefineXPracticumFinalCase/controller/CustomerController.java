package com.final_case.DefineXPracticumFinalCase.controller;

import com.final_case.DefineXPracticumFinalCase.dto.CustomerDto;
import com.final_case.DefineXPracticumFinalCase.dto.converter.CustomerConverter;
import com.final_case.DefineXPracticumFinalCase.model.Customer;
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
    private final CustomerConverter converter;

    @PostMapping("/save")
    public ResponseEntity<CustomerDto> save(@Valid @RequestBody Customer customerRequest){
        log.debug("REST Request to save Customer: {}",customerRequest);

        return ResponseEntity.ok(converter.convert(customerService.save(customerRequest)));
    }


    @GetMapping("/all")
    public ResponseEntity<List<CustomerDto>> findAll(){
        log.debug("REST Request to get all Customer");
        return ResponseEntity.ok(converter.convert(customerService.findAll()));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<CustomerDto> update (@PathVariable(name = "id") UUID id, @RequestBody Customer customerRequest){
        log.debug("REST Request to update Customer : {}", customerRequest);
        return ResponseEntity.ok(converter.convert(customerService.update(id,customerRequest)));

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        log.debug("REST Request to delete Customer : {}", id);
        customerService.delete(id);
    }




}
