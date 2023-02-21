package com.final_case.DefineXPracticumFinalCase.service.impl;

import com.final_case.DefineXPracticumFinalCase.dto.ExistCreditRequest;
import com.final_case.DefineXPracticumFinalCase.dto.NewCreditRequest;
import com.final_case.DefineXPracticumFinalCase.enumeration.CreditMessage;
import com.final_case.DefineXPracticumFinalCase.model.Credit;
import com.final_case.DefineXPracticumFinalCase.model.Customer;
import com.final_case.DefineXPracticumFinalCase.model.FinancialInformation;
import com.final_case.DefineXPracticumFinalCase.service.CreditService;
import com.final_case.DefineXPracticumFinalCase.service.CustomerService;
import com.final_case.DefineXPracticumFinalCase.service.FinancialInformationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

class CreditApplicationServiceImplTest {

    private CustomerService customerService;
    private FinancialInformationService financialInformationService;
    private CreditService creditService;
    private CreditApplicationServiceImpl creditApplicationService;

    @BeforeEach
    void setUp() {
        financialInformationService = mock(FinancialInformationServiceImpl.class);
        customerService = mock(CustomerServiceImpl.class);
        creditService = mock(CreditServiceImpl.class);
        creditApplicationService = new CreditApplicationServiceImpl(customerService,financialInformationService,creditService);
    }

    @Test
    void createNewCredit_shouldReturnCredit_accepted() {
        NewCreditRequest creditRequest = NewCreditRequest.builder()
                .name("name")
                .surname("surname")
                .identityNumber("2222222222")
                .callNumber("05059656565")
                .birthDay(LocalDate.ofEpochDay(1998-11-03))
                .salary(5000)
                .assurance(10000)
                .build();

        Customer customerRequestTest = Customer.builder()
                .id(UUID.randomUUID())
                .name("name")
                .surname("surname")
                .identityNumber("2222222222")
                .callNumber("05059656565")
                .birthDay(LocalDate.ofEpochDay(1998-11-03))
                .build();

        FinancialInformation FITestRequest = FinancialInformation.builder()
                .id(UUID.randomUUID())
                .salary(5000)
                .assurance(10000)
                .creditScore(1000)
                .customer(customerRequestTest)
                .build();

        Credit credit = Credit.builder()
                .id(UUID.randomUUID())
                .creditLimit(25000)
                .message(CreditMessage.ACCEPTED)
                .isAccepted(true)
                .customer(customerRequestTest)
                .build();

        Mockito.when(customerService.save(any())).thenReturn(customerRequestTest);
        Mockito.when(financialInformationService.save(any())).thenReturn(FITestRequest);
        Mockito.when(creditService.save(any())).thenReturn(credit);

        Credit result = creditApplicationService.createNewCredit(creditRequest);

        assertEquals(credit,result);
    }

    @Test
    void updateExistCredit() {

    }

    @Test
    void getExistCredit_shoulbeReturn() {
        ExistCreditRequest creditRequest= ExistCreditRequest.builder()
                .identityNumber("2222222222")
                .birthDay(LocalDate.ofEpochDay(1998-11-03))
                .build();

        FinancialInformation financialInformation = FinancialInformation.builder().build();

        Credit credit = Credit.builder()
                .id(UUID.randomUUID())
                .creditLimit(25000)
                .message(CreditMessage.ACCEPTED)
                .isAccepted(true)
                .customer(Customer.builder().build())
                .build();

        Customer customerTestRequest = Customer.builder()
                .id(UUID.randomUUID())
                .name("name")
                .surname("surname")
                .identityNumber("2222222222")
                .callNumber("05059656565")
                .birthDay(LocalDate.ofEpochDay(1998-11-03))
                .financialInformation(financialInformation)
                .credit(credit)
                .build();


        Mockito.when(customerService.findByIdentityNumberAndBirthDay(creditRequest)).thenReturn(customerTestRequest);

        Credit result = creditApplicationService.getExistCredit(creditRequest);

        assertEquals(credit,result);
    }
}