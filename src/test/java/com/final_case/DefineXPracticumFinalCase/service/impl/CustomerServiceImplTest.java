package com.final_case.DefineXPracticumFinalCase.service.impl;

import com.final_case.DefineXPracticumFinalCase.dto.CustomerPersonalInfo;
import com.final_case.DefineXPracticumFinalCase.exception.CustomerNotFoundException;
import com.final_case.DefineXPracticumFinalCase.exception.ExistsCustomerException;
import com.final_case.DefineXPracticumFinalCase.model.Customer;
import com.final_case.DefineXPracticumFinalCase.repository.CustomerRepository;
import com.final_case.DefineXPracticumFinalCase.service.CreditScoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerService;
    @Mock
    private CreditScoreService creditScoreService;
    @Mock
    private CustomerRepository customerRepository;

    private UUID customerId = UUID.randomUUID();
    Customer customer = Customer.builder()
            .id(customerId)
            .name("name")
            .surname("surname")
            .identityNumber("2222222222")
            .callNumber("05059656565")
            .birthDay(new GregorianCalendar(1998, 3, 30).getTime())
            .salary(5000)
            .assurance(10000)
            .creditScore(1000)
            .build();

    @BeforeEach
    public void setUp(){
        customerRepository = mock(CustomerRepository.class);
        creditScoreService = mock(CreditScoreServiceImpl.class);
        customerService = new CustomerServiceImpl(customerRepository,creditScoreService);
    }

    @Test
    void saveCustemer_shouldReturnCustomer() {
        Customer testCustomer = Customer.builder()
                .name("name")
                .surname("surname")
                .identityNumber("2222222222")
                .callNumber("05059656565")
                .birthDay(new GregorianCalendar(1998, 3, 30).getTime())
                .salary(5000)
                .assurance(10000)
                .creditScore(1000)
                .build();
        when(customerRepository.existsByIdentityNumber(customer.getIdentityNumber())).thenReturn(false);
        when(creditScoreService.getCreditScore()).thenReturn(testCustomer.getCreditScore());
        when(customerRepository.save(testCustomer)).thenReturn(customer);
        Customer result = customerService.save(customer);
        assertEquals(result, customer);
    }

    @Test
    void updateCustomer_shouldReturnCustomer() {
        CustomerPersonalInfo customerPersonalInfo = CustomerPersonalInfo.builder()
                .surname("surnameUpdate")
                .callNumber("05059650000")
                .build();

        Customer updatedcustomer = Customer.builder()
                .id(customerId)
                .name("name")
                .surname("surnameUpdate")
                .identityNumber("2222222222")
                .callNumber("05059650000")
                .birthDay(new GregorianCalendar(1998, 3, 30).getTime())
                .salary(5000)
                .assurance(10000)
                .creditScore(1000)
                .build();


        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.ofNullable(customer));
        Mockito.when(customerRepository.save(updatedcustomer)).thenReturn(updatedcustomer);
        Customer result = customerService.updatePersonalInformation(customer.getId(), customerPersonalInfo);

        assertEquals(updatedcustomer,result);
    }


    @Test
    void findAll_shouldReturnCustomerList() {
        Customer testCustomer = Customer.builder()
                .id(UUID.randomUUID())
                .name("name1")
                .surname("surname1")
                .identityNumber("2552222222")
                .callNumber("05059657575")
                .birthDay(new GregorianCalendar(1998, 3, 30).getTime())
                .salary(3000)
                .assurance(30000)
                .creditScore(750)
                .build();


        List<Customer> testList = List.of(testCustomer,customer);

        Mockito.when(customerRepository.findAll()).thenReturn(testList);

        List<Customer> result =customerService.findAll();

        assertEquals(testList,result);
    }

    @Test
    void findByIdentityNumberAndBirthDay_shouldReturnCustomer() {
        when(customerRepository.findByIdentityNumberAndBirthDay(customer.getIdentityNumber(),customer.getBirthDay())).thenReturn(Optional.ofNullable(customer));

        Customer result = customerService.findByIdentityNumberAndBirthDay(customer.getIdentityNumber(),customer.getBirthDay());

        assertEquals(customer,result);

    }


    @Test
    void findByIdentityNumber_shouldReturnCustomer() {
        when(customerRepository.findByIdentityNumber(customer.getIdentityNumber())).thenReturn(Optional.ofNullable(customer));

        Customer result = customerService.findByIdentityNumber(customer.getIdentityNumber());

        assertEquals(customer,result);
    }

    //Exception tests of methods

    @Test
    void updateCustomer_whenIdDoesNotExist_shouldThrowCustomerNotFoundException() {
        CustomerPersonalInfo customerPersonalInfo = CustomerPersonalInfo.builder()
                .surname("surnameUpdate")
                .callNumber("05059650000")
                .build();



        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class,()->customerService.updatePersonalInformation(customer.getId(), customerPersonalInfo));
    }


    @Test
    void saveCustemer_whenCustomerExist_shouldThrowExistsCustomerException() {

        when(customerRepository.existsByIdentityNumber(customer.getIdentityNumber())).thenReturn(true);
        assertThrows(ExistsCustomerException.class, ()->customerService.save(customer));
    }

    @Test
    void findByIdentityNumberAndBirthDay_whenIdentityNumberOrBirthDayDoesNotExist_shouldThrowCustomerNotFoundException() {
        when(customerRepository.findByIdentityNumberAndBirthDay(customer.getIdentityNumber(),customer.getBirthDay())).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class,
                ()->customerService.findByIdentityNumberAndBirthDay(customer.getIdentityNumber(),customer.getBirthDay()));

    }

    @Test
    void findByIdentityNumber_whenIdDoesNotExist_shouldThrowCustomerNotFoundException() {
        when(customerRepository.findByIdentityNumber(customer.getIdentityNumber())).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class,()->customerService.findByIdentityNumber(customer.getIdentityNumber()));
    }
}