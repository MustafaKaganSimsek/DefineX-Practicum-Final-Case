package com.final_case.DefineXPracticumFinalCase.service.impl;

import com.final_case.DefineXPracticumFinalCase.model.Customer;
import com.final_case.DefineXPracticumFinalCase.repository.CustomerRepository;
import com.final_case.DefineXPracticumFinalCase.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CustomerServiceImplTest {

    private CustomerService customerService;
    private CustomerRepository customerRepository;

    Customer customer = Customer.builder()
            .id(UUID.randomUUID())
            .name("name")
            .surname("surname")
            .identityNumber("2222222222")
            .callNumber("05059656565")
            .birthDay(LocalDate.ofEpochDay(1998-11-03))
            .build();

    @BeforeEach
    public void setUp(){
        customerRepository = mock(CustomerRepository.class);
        customerService = new CustomerServiceImpl(customerRepository);
    }

    @Test
    void saveCustemer_shouldReturnCustomer() {

        Mockito.when(customerRepository.save(customer)).thenReturn(customer);
        Customer result = customerService.save(customer);
        assertEquals(result, customer);
    }


    @Test
    void updateCustomer_shouldReturnDifrentSurnameAndCallNumber() {
        Customer testcustomer = Customer.builder()
                .surname("surnameUpdate")
                .callNumber("05059650000")
                .build();

        Mockito.when(customerRepository.findById(customer.getId())).thenReturn(Optional.ofNullable(customer));
        Mockito.when(customerRepository.save())
        Customer result = customerService.update(customer.getId(),testcustomer);

    }

    @Test
    void delete() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findByIdentityNumberAndBirthDay() {
    }

    @Test
    void findByIdentityNumber() {
    }
}