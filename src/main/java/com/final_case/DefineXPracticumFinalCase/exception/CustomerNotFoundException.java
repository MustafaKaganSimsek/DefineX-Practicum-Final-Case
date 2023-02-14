package com.final_case.DefineXPracticumFinalCase.exception;

import org.springframework.data.crossstore.ChangeSetPersister;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
