package com.final_case.DefineXPracticumFinalCase.service.impl;


import com.final_case.DefineXPracticumFinalCase.service.CreditScoreService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Random;
@Log4j2
@Service
public class CreditScoreServiceImpl implements CreditScoreService {


    @Override
    public double getCreditScore(){
        log.debug("Request to get CreditScore");
        return ramdomNumberGenerator();
    }

    private double ramdomNumberGenerator(){
        return new Random().nextDouble(1500)+1;
    }
}
