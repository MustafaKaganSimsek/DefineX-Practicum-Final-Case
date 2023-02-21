package com.final_case.DefineXPracticumFinalCase.service;

import com.final_case.DefineXPracticumFinalCase.dto.ExistCreditRequest;
import com.final_case.DefineXPracticumFinalCase.model.Customer;
import com.final_case.DefineXPracticumFinalCase.repository.CustomerRepository;
import com.final_case.DefineXPracticumFinalCase.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class CustomerServiceTest {

    private CustomerService customerService;
    private CustomerRepository customerRepository;

    private final UUID id = UUID.randomUUID();

    @BeforeEach
    public void setUp(){
        customerRepository = mock(CustomerRepository.class);
        customerService = new CustomerServiceImpl(customerRepository);
    }

    @Test
    void saveCustemer_shouldReturnCustomer() {
        Customer customerRequestTest = Customer.builder()
                .id(id)
                .name("name")
                .surname("surname")
                .identityNumber("2222222222")
                .callNumber("05059656565")
                .birthDay(LocalDate.ofEpochDay(1998-11-03))
                .build();
        Mockito.when(customerRepository.save(customerRequestTest)).thenReturn(customerRequestTest);

        Customer result = customerService.save(customerRequestTest);

        assertEquals(result, customerRequestTest);

//        Mockito.verify(customerService).save(customerTest);
//        Mockito.verify(customerRepository).save(customerTest);
    }

    @Test()
    void update() {
//        Customer customer = Customer.builder()
//                .id(id)
//                .name("name")
//                .surname("surname")
//                .identityNumber("2222222222")
//                .callNumber("05059656565")
//                .birthDay(LocalDate.ofEpochDay(1998-11-03))
//                .build();
//
//        Customer customerRequestTest = Customer.builder()
//                .id(id)
//                .name("nameup")
//                .surname("surnameup")
//                .identityNumber("2222222233")
//                .callNumber("05059656599")
//                .birthDay(LocalDate.ofEpochDay(1999-01-26))
//                .build();
//        Mockito.when(customerRepository.findById(id)).thenReturn(Optional.ofNullable(customer));
//        Mockito.when(customerRepository.save(customerRequestTest)).thenReturn(customerRequestTest);
//
//        Customer result = customerService.update(id,customerRequestTest);
//
//        assertEquals(customer.getId(),result.getId());
//        assertEquals(customerRequestTest.getName(),result.getName());
//        assertEquals(customerRequestTest.getSurname(),result.getSurname());
//        assertEquals(customerRequestTest.getIdentityNumber(),result.getIdentityNumber());
//        assertEquals(customerRequestTest.getCallNumber(),result.getCallNumber());
//        assertEquals(customer.getBirthDay(),result.getBirthDay());
    }

    @Test
    void findAllCustomers_shouldReturnCustomerList() {
        Customer testRequest = Customer.builder()
                .id(UUID.randomUUID())
                .name("name")
                .surname("surname")
                .identityNumber("2222222222")
                .callNumber("05059656565")
                .birthDay(LocalDate.ofEpochDay(1998-11-03))
                .build();

        Customer testRequest1 = Customer.builder()
                .id(UUID.randomUUID())
                .name("name")
                .surname("surname")
                .identityNumber("2222222222")
                .callNumber("05059656565")
                .birthDay(LocalDate.ofEpochDay(1998-11-03))
                .build();

        List<Customer> testList = List.of(testRequest,testRequest1);

        Mockito.when(customerRepository.findAll()).thenReturn(testList);

        List<Customer> result =customerService.findAll();

        assertEquals(testList,result);


    }

    @Test
    void findByIdentityNumberAndBirthDay_shouldReturnCustomer() {
        Customer testRequest = Customer.builder()
                .id(id)
                .name("name")
                .surname("surname")
                .identityNumber("2222222222")
                .callNumber("05059656565")
                .birthDay(LocalDate.ofEpochDay(1998-11-03))
                .build();

        ExistCreditRequest existCreditRequest = ExistCreditRequest.builder()
                .birthDay(testRequest.getBirthDay())
                .identityNumber(testRequest.getIdentityNumber())
                .build();

        Mockito.when(customerRepository
                .findByIdentityNumberAndBirthDay(existCreditRequest.getIdentityNumber(),existCreditRequest.getBirthDay()))
                .thenReturn(Optional.of(testRequest));

        Customer result = customerService
                .findByIdentityNumberAndBirthDay(existCreditRequest);

        assertEquals(testRequest,result);
    }
}