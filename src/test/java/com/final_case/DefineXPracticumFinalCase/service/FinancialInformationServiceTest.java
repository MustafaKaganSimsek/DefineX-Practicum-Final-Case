package com.final_case.DefineXPracticumFinalCase.service;

import com.final_case.DefineXPracticumFinalCase.model.Customer;
import com.final_case.DefineXPracticumFinalCase.model.FinancialInformation;
import com.final_case.DefineXPracticumFinalCase.repository.FinancialInformationRepository;
import com.final_case.DefineXPracticumFinalCase.service.impl.FinancialInformationServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

@Log4j2
class FinancialInformationServiceTest {

    private FinancialInformationService financialInformationService;
    private CreditScoreService creditScoreService;
    private FinancialInformationRepository financialInformationRepository;

    private final UUID id = UUID.randomUUID();

    @BeforeEach
    public void setUp(){
        creditScoreService = mock(CreditScoreService.class);
        financialInformationRepository = mock(FinancialInformationRepository.class);
        financialInformationService = new FinancialInformationServiceImpl(creditScoreService,financialInformationRepository);
    }

    @Test
    void save() {
        FinancialInformation testRequest = FinancialInformation.builder()
                .id(id)
                .salary(5000)
                .assurance(10000)
                .creditScore(1000)
                .customer(Customer.builder()
                        .build())
                .build();
        Mockito.when(creditScoreService.getCreditScore()).thenReturn(1000.0);
        Mockito.when(financialInformationRepository.save(any())).thenReturn(testRequest);

        log.debug(financialInformationService.save(testRequest));
        Object result = financialInformationService.save(testRequest);

        assertEquals(testRequest,result);
    }

    @Test
    void update() {

    }

    @Test
    void findAll() {
        FinancialInformation testRequest = FinancialInformation.builder()
                .id(id)
                .salary(5000)
                .assurance(10000)
                .creditScore(1000)
                .customer(Customer.builder()
                        .build())
                .build();
        FinancialInformation testRequest1 = FinancialInformation.builder()
                .id(id)
                .salary(5000)
                .assurance(10000)
                .creditScore(1000)
                .customer(Customer.builder()
                        .build())
                .build();

        List<FinancialInformation> testList=List.of(testRequest1,testRequest);

        Mockito.when(financialInformationRepository.findAll()).thenReturn(testList);

        List<FinancialInformation> result = financialInformationService.findAll();

        assertEquals(result,testList);
    }
}