package com.final_case.DefineXPracticumFinalCase.controller;

import com.final_case.DefineXPracticumFinalCase.dto.CustomerDto;
import com.final_case.DefineXPracticumFinalCase.dto.converter.CustomerConverter;
import com.final_case.DefineXPracticumFinalCase.model.Customer;
import com.final_case.DefineXPracticumFinalCase.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping("creditapp/customer")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerConverter converter;

    @PostMapping("/save")
    public ResponseEntity<CustomerDto> save(@Valid @RequestBody Customer customer){
        log.debug("REST Request to save Customer: {}",customer);
        return ResponseEntity.ok(converter.convert(customerService.save(customer)));
    }


    @GetMapping("/all")
    public ResponseEntity<List<CustomerDto>> findAll(){
        log.debug("REST Request to get all Customer");
        return ResponseEntity.ok(converter.convert(customerService.findAll()));
    }

    @PostMapping("/update")
    public ResponseEntity<Customer> update (@Valid @RequestBody Customer customer){
        log.debug("REST Request to update Customer : {}", customer);
        customerService.update(customer);
        return ResponseEntity.ok(customerService.update(customer));

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        log.debug("REST Request to delete Customer : {}", id);
        customerService.delete(id);
    }



}
