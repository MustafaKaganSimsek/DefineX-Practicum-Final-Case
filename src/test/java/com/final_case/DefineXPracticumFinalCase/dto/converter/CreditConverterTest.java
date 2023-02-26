package com.final_case.DefineXPracticumFinalCase.dto.converter;

import com.final_case.DefineXPracticumFinalCase.dto.CreditDto;
import com.final_case.DefineXPracticumFinalCase.enumeration.CreditMessage;
import com.final_case.DefineXPracticumFinalCase.model.Credit;
import com.final_case.DefineXPracticumFinalCase.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.GregorianCalendar;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreditConverterTest {
    @InjectMocks
    private CreditConverter creditConverter;
    @Test
    void convertCredit_shouldReturnCreditDto() {
        Customer customer = Customer.builder()
                .id(UUID.randomUUID())
                .build();

        Credit credit = Credit.builder()
                .id(UUID.randomUUID())
                .creditLimit(25000)
                .message(CreditMessage.ACCEPTED)
                .isAccepted(true)
                .customer(customer)
                .build();

        CreditDto result = creditConverter.convert(credit);

        assertEquals(credit.getId(),result.getId());
        assertEquals(credit.getCreditLimit(),result.getCreditLimit());
        assertEquals(credit.getMessage(),result.getMessage());
        assertEquals(credit.isAccepted(),result.isAccepted());
        assertEquals(credit.getCustomer().getId(),result.getCustomerId());


    }
}