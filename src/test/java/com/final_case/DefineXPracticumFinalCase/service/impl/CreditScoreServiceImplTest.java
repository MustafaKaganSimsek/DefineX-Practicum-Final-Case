package com.final_case.DefineXPracticumFinalCase.service.impl;

import com.final_case.DefineXPracticumFinalCase.service.CreditScoreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreditScoreServiceImplTest {
    @InjectMocks
    private CreditScoreServiceImpl creditScoreService;

    @Test
    void getCreditScore_shouldReturnDoubleNumber_between0and1500() {
        double result = creditScoreService.getCreditScore();
        assertTrue(result>0 && result<=1500);
    }
}