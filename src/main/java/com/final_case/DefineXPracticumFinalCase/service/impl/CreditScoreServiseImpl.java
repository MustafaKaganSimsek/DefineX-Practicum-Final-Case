package com.final_case.DefineXPracticumFinalCase.service.impl;


import com.final_case.DefineXPracticumFinalCase.model.Customer;
import com.final_case.DefineXPracticumFinalCase.service.CreditScoreService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CreditScoreServiseImpl implements CreditScoreService {


    @Override
    public double getCreditScore(){
        return ramdomNumberGenerator();
    }

    private double ramdomNumberGenerator(){
        return new Random().nextDouble(1500);
    }
}
