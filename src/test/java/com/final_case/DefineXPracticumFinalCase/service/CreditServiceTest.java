package com.final_case.DefineXPracticumFinalCase.service;

import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

class CreditServiceTest {
//    private CreditRepository creditRepository;
//    private CreditService creditService;
//
//    private final UUID id = UUID.randomUUID();
//
//    @BeforeEach
//    void setUp(){
//        creditRepository = mock(CreditRepository.class);
//        creditService =new CreditServiceImpl(creditRepository);
//    }
//
//    @Test
//    void save() {
////        Credit testRequest = Credit.builder()
////                .id(id)
////                .
////                .build();
////        Mockito.when(customerRepository.save(customerRequestTest)).thenReturn(customerRequestTest);
////
////        Customer result = customerService.save(customerRequestTest);
////
////        assertEquals(result, customerRequestTest);
//    }
//
//    @Test
//    void update() {
//    }
//
//
//
//    @Test
//    void createNewCredit_shouldReturnCredit_denied() {
//        NewCreditRequest creditRequest = NewCreditRequest.builder()
//                .name("name")
//                .surname("surname")
//                .identityNumber("2222222222")
//                .callNumber("05059656565")
//                .birthDay(LocalDate.ofEpochDay(1998-11-03))
//                .salary(5000)
//                .assurance(10000)
//                .build();
//
//        Customer customerTestRequest = Customer.builder()
//                .id(id)
//                .name("name")
//                .surname("surname")
//                .identityNumber("2222222222")
//                .callNumber("05059656565")
//                .birthDay(LocalDate.ofEpochDay(1998-11-03))
//                .build();
//
//        FinancialInformation FITestRequest = FinancialInformation.builder()
//                .id(id)
//                .salary(5000)
//                .assurance(10000)
//                .creditScore(499)
//                .customer(customerTestRequest)
//                .build();
//
//        Credit credit = Credit.builder()
//                .id(id)
//                .creditLimit(0)
//                .message(CreditMessage.DENIED)
//                .isAccepted(false)
//                .customer(customerTestRequest)
//                .build();

//        Mockito.when(customerService.save(any())).thenReturn(customerTestRequest);
//        Mockito.when(financialInformationService.save(any())).thenReturn(FITestRequest);
//        Mockito.when(creditRepository.save(any())).thenReturn(credit);
//
//        Credit result = creditService.createNewCredit(creditRequest);
//
//        assertEquals(credit,result);
//    }

    @Test
    void getExistCredit() {
//        ExistCreditRequest creditRequest= ExistCreditRequest.builder()
//                .identityNumber("2222222222")
//                .birthDay(LocalDate.ofEpochDay(1998-11-03))
//                .build();
//
//        FinancialInformation financialInformation = FinancialInformation.builder().build();
//        financialInformation.s
//        Customer customerTestRequest = Customer.builder()
//                .id(id)
//                .name("name")
//                .surname("surname")
//                .identityNumber("2222222222")
//                .callNumber("05059656565")
//                .birthDay(LocalDate.ofEpochDay(1998-11-03))
//                .financialInformation(
//                .build();
//        Credit credit = Credit.builder()
//                .id(id)
//                .creditLimit(25000)
//                .message(CreditMessage.ACCEPTED)
//                .isAccepted(true)
//                .customer(Customer.builder().build())
//                .build();
//
//        Mockito.when(customerService.findByIdentityNumberAndBirthDay(creditRequest)).thenReturn(Customer.builder().build());
//
//        Credit result = creditService.getExistCredit(creditRequest);
//
//        assertNotNull(result);

    }
}