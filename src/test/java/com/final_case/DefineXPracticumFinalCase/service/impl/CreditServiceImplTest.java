package com.final_case.DefineXPracticumFinalCase.service.impl;

import com.final_case.DefineXPracticumFinalCase.dto.CreateCustomerRequest;
import com.final_case.DefineXPracticumFinalCase.dto.CustomerFinancialInfo;
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

import java.util.GregorianCalendar;
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
                .birthDay(new GregorianCalendar(1998, 3, 30).getTime())
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
    void updateCredit_shouldReturnCredit(){
        Credit updatedCredit = Credit.builder()
                .id(creditId)
                .creditLimit(11000)
                .message(CreditMessage.ACCEPTED)
                .isAccepted(true)
                .customer(customer)
                .build();

        CustomerFinancialInfo financialInfo
                = CustomerFinancialInfo.builder()
                .salary(4000)
                .assurance(10000)
                .creditScore(550)
                .build();

        when(creditRepository.findById(creditId)).thenReturn(Optional.ofNullable(credit));
        when(creditRepository.save(updatedCredit)).thenReturn(updatedCredit);

        Credit result = creditService.update(creditId,financialInfo
        );

        assertEquals(updatedCredit,result);
    }

    @Test
    void createCreditForNewCustomer_shouldReturnCredit() {
        CreateCustomerRequest customerRequest = CreateCustomerRequest.builder()
                .name("name")
                .surname("surname")
                .identityNumber("2222222222")
                .callNumber("05059656565")
                .birthDay(new GregorianCalendar(1998, 3, 30).getTime())
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
                .birthDay(new GregorianCalendar(1998, 3, 30).getTime())
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
    void updateWithCustomer_shoulbeReturnCredit(){
        Customer updatedCustomer = Customer.builder()
                .id(customer.getId())
                .name("name")
                .surname("surname")
                .identityNumber("2222222222")
                .callNumber("05059656565")
                .birthDay(new GregorianCalendar(1998, 3, 30).getTime())
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

        CustomerFinancialInfo financialInfo
                = CustomerFinancialInfo.builder()
                .salary(updatedCustomer.getSalary())
                .assurance(updatedCustomer.getAssurance())
                .creditScore(updatedCustomer.getCreditScore())
                .build();

        when(customerService.findByIdentityNumber(customer.getIdentityNumber())).thenReturn(customer);
        when(customerService.updateFinancialInformation(customer.getId(),financialInfo
        )).thenReturn(updatedCustomer);
        when(creditRepository.save(updatedCredit)).thenReturn(updatedCredit);

        Credit result = creditService.updateWithCustomer(customer.getIdentityNumber(),financialInfo
        );

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
    void updateCredit_whenCreditDoesNotExistId_shouldThrowCreditNotFoundExeption(){
        Credit updatedCredit = Credit.builder()
                .id(creditId)
                .creditLimit(10000)
                .message(CreditMessage.ACCEPTED)
                .isAccepted(true)
                .customer(customer)
                .build();

        CustomerFinancialInfo financialInfo
                = CustomerFinancialInfo.builder()
                .salary(4000)
                .assurance(10000)
                .creditScore(550)
                .build();
        when(creditRepository.findById(creditId)).thenReturn(Optional.empty());

        assertThrows(CreditNotFoundExeption.class,()->creditService.update(creditId,financialInfo
        ));
    }

    @Test
    void createCreditForExistCustomer_whenCreditNotNull_shouldThrowExistCreditException(){

        when(customerService.findByIdentityNumber(customer.getIdentityNumber())).thenReturn(customer);

        assertThrows(ExistCreditException.class,()->creditService.createCreditForExistCustomer(customer.getIdentityNumber()));
    }

}