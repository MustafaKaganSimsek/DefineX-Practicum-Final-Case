package com.final_case.DefineXPracticumFinalCase.service;

import com.final_case.DefineXPracticumFinalCase.service.impl.CreditScoreServiceImpl;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CreditScoreServiceTest {
    private CreditScoreService creditScoreService;

    @BeforeEach
    public void setUp(){
        creditScoreService = new CreditScoreServiceImpl();
    }
    @Test
    void getCreditScore_shouldReturnDouble() {
        double result = creditScoreService.getCreditScore();
        assertTrue(result>0 && result<=1500);}
}