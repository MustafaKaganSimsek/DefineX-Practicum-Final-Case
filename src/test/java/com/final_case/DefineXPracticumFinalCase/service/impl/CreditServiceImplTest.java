package com.final_case.DefineXPracticumFinalCase.service.impl;

import com.final_case.DefineXPracticumFinalCase.dto.CustomerFinancialInfoDto;
import com.final_case.DefineXPracticumFinalCase.enumeration.CreditMessage;
import com.final_case.DefineXPracticumFinalCase.exception.CreditNotFoundExeption;
import com.final_case.DefineXPracticumFinalCase.exception.ExistCreditException;
import com.final_case.DefineXPracticumFinalCase.model.Credit;
import com.final_case.DefineXPracticumFinalCase.model.Customer;
import com.final_case.DefineXPracticumFinalCase.repository.CreditRepository;
import com.final_case.DefineXPracticumFinalCase.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreditServiceImplTest {
    @Mock
    private CustomerService customerService;
    @Mock
    private CreditRepository creditRepository;
    @InjectMocks
    private CreditServiceImpl creditService;

    private UUID customerId = UUID.randomUUID();
    private UUID creditId = UUID.randomUUID();

    private Customer customer = new Customer();

    private Credit credit = new Credit();

    @BeforeEach
    void setUp() {

        customer = Customer.builder()
                .id(customerId)
                .name("name")
                .surname("surname")
                .identityNumber("2222222222")
                .callNumber("05059656565")
                .birthDay(LocalDate.ofEpochDay(1998-11-03))
                .salary(5000)
                .assurance(10000)
                .creditScore(1000)
                .build();

        credit = Credit.builder()
                .id(creditId)
                .creditLimit(25000)
                .message(CreditMessage.ACCEPTED)
                .isAccepted(true)
                .customer(customer)
                .build();

        customer.setCredit(credit);
    }



    @Test
    void saveCredit_shouldReturnCredit(){
        when(creditRepository.existsByCustomer(credit.getCustomer())).thenReturn(false);
        when(creditRepository.save(credit)).thenReturn(credit);

        Credit result = creditService.save(credit);

        assertEquals(result,credit);
    }

    @Test
    void updateCredit_shouldReturnCredit(){
        Credit updatedCredit = Credit.builder()
                .id(creditId)
                .creditLimit(10000)
                .message(CreditMessage.ACCEPTED)
                .isAccepted(true)
                .customer(customer)
                .build();

        when(creditRepository.findById(creditId)).thenReturn(Optional.ofNullable(credit));
        when(creditRepository.save(updatedCredit)).thenReturn(updatedCredit);

        Credit result = creditService.update(creditId,updatedCredit);

        assertEquals(updatedCredit,result);
    }

    @Test
    void createCreditForNewCustomer_shouldReturnCredit() {
        Customer customerRequest = Customer.builder()
                .name("name")
                .surname("surname")
                .identityNumber("2222222222")
                .callNumber("05059656565")
                .birthDay(LocalDate.ofEpochDay(1998-11-03))
                .salary(5000)
                .assurance(10000)
                .build();
        Credit creditRequest = Credit.builder()
                .creditLimit(25000)
                .message(CreditMessage.ACCEPTED)
                .isAccepted(true)
                .customer(customer)
                .build();


        when(customerService.save(customerRequest)).thenReturn(customer);
        when(creditRepository.save(creditRequest)).thenReturn(credit);

        Credit result = creditService.createCreditForNewCustomer(customerRequest);


        assertEquals(credit,result);
    }

    @Test
    void createCreditForExistCustomer_returnShouldCredit(){
        Customer customerRequest = Customer.builder()
                .id(customerId)
                .name("name")
                .surname("surname")
                .identityNumber("2222222222")
                .callNumber("05059656565")
                .birthDay(LocalDate.ofEpochDay(1998-11-03))
                .salary(5000)
                .assurance(10000)
                .creditScore(1000)
                .build();
        Credit creditRequest = Credit.builder()
                .creditLimit(25000)
                .message(CreditMessage.ACCEPTED)
                .isAccepted(true)
                .customer(customerRequest)
                .build();
        when(customerService.findByIdentityNumber(customerRequest.getIdentityNumber())).thenReturn(customerRequest);
        when(creditRepository.save(creditRequest)).thenReturn(credit);

        Credit resurlt = creditService.createCreditForExistCustomer(customerRequest.getIdentityNumber());
        assertEquals(resurlt,credit);
    }

    @Test
    void updateExistCredit_shoulbeReturnCredit(){
        Customer updatedCustomer = Customer.builder()
                .id(customer.getId())
                .name("name")
                .surname("surname")
                .identityNumber("2222222222")
                .callNumber("05059656565")
                .birthDay(LocalDate.ofEpochDay(1998-11-03))
                .salary(10000)
                .assurance(15000)
                .creditScore(1000)
                .credit(credit)
                .build();
        Credit updatedCredit = Credit.builder()
                .id(creditId)
                .creditLimit(47500.0)
                .message(CreditMessage.ACCEPTED)
                .isAccepted(true)
                .customer(updatedCustomer)
                .build();

        CustomerFinancialInfoDto financialInformation= CustomerFinancialInfoDto.builder()
                .salary(updatedCustomer.getSalary())
                .assurance(updatedCustomer.getAssurance())
                .build();

        when(customerService.findByIdentityNumber(customer.getIdentityNumber())).thenReturn(customer);
        when(customerService.updateFinancialInformation(customer.getId(),financialInformation)).thenReturn(updatedCustomer).thenReturn(updatedCustomer);
        when(creditRepository.save(updatedCredit)).thenReturn(updatedCredit);

        Credit result = creditService.updateExistCredit(customer.getIdentityNumber(),financialInformation);

        assertEquals(result,updatedCredit);
    }




    @Test
    void getExistCredit_shoulbeReturnCredit() {

        when(customerService.findByIdentityNumberAndBirthDay(customer.getIdentityNumber(),customer.getBirthDay())).thenReturn(customer);
        Credit result = creditService.getExistCredit(customer.getIdentityNumber(),customer.getBirthDay());

        assertEquals(credit,result);
    }

    //Exception tests o methods

    @Test
    void saveCredit_whenCreditExist_shouldThrowExistCreditException(){
        when(creditRepository.existsByCustomer(credit.getCustomer())).thenReturn(true);

        assertThrows(ExistCreditException.class,()->creditService.save(credit));
    }

    @Test
    void updateCredit_whenCreditDoesNotExistId_shouldThrowCreditNotFoundExeption(){
        Credit updatedCredit = Credit.builder()
                .id(creditId)
                .creditLimit(10000)
                .message(CreditMessage.ACCEPTED)
                .isAccepted(true)
                .customer(customer)
                .build();

        when(creditRepository.findById(creditId)).thenReturn(Optional.empty());

        assertThrows(CreditNotFoundExeption.class,()->creditService.update(creditId,updatedCredit));
    }

    @Test
    void createCreditForExistCustomer_whenCreditNotNull_shouldThrowExistCreditException(){

        when(customerService.findByIdentityNumber(customer.getIdentityNumber())).thenReturn(customer);

        assertThrows(ExistCreditException.class,()->creditService.createCreditForExistCustomer(customer.getIdentityNumber()));
    }

    @Test
    void updateExistCredit_whenCreditNull_shouldThrowCreditNotFoundExeption(){
        Customer testCustomer = Customer.builder()
                .id(customer.getId())
                .name("name")
                .surname("surname")
                .identityNumber("2222222222")
                .callNumber("05059656565")
                .birthDay(LocalDate.ofEpochDay(1998-11-03))
                .salary(10000)
                .assurance(15000)
                .creditScore(1000)
                .build();


        CustomerFinancialInfoDto financialInformation= CustomerFinancialInfoDto.builder()
                .salary(testCustomer.getSalary())
                .assurance(testCustomer.getAssurance())
                .build();

        when(customerService.findByIdentityNumber(testCustomer.getIdentityNumber())).thenReturn(testCustomer);


        assertThrows(CreditNotFoundExeption.class,()->creditService.updateExistCredit(customer.getIdentityNumber(),financialInformation));
    }

    @Test
    void getExistCredit_whenCreditNull_shouldThrowCreditNotFoundExeption() {
        Customer testCustomer = Customer.builder()
                .id(customer.getId())
                .name("name")
                .surname("surname")
                .identityNumber("2222222222")
                .callNumber("05059656565")
                .birthDay(LocalDate.ofEpochDay(1998-11-03))
                .salary(10000)
                .assurance(15000)
                .creditScore(1000)
                .build();

        when(customerService.findByIdentityNumberAndBirthDay(testCustomer.getIdentityNumber(),testCustomer.getBirthDay())).thenReturn(testCustomer);

        assertThrows(CreditNotFoundExeption.class,()->creditService.getExistCredit(customer.getIdentityNumber(),customer.getBirthDay()));
    }

}