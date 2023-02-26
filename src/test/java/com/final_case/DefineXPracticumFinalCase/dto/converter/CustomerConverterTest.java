package com.final_case.DefineXPracticumFinalCase.dto.converter;

import com.final_case.DefineXPracticumFinalCase.dto.CreditDto;
import com.final_case.DefineXPracticumFinalCase.dto.CustomerDto;
import com.final_case.DefineXPracticumFinalCase.enumeration.CreditMessage;
import com.final_case.DefineXPracticumFinalCase.model.Credit;
import com.final_case.DefineXPracticumFinalCase.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomerConverterTest {
    @InjectMocks
    private CustomerConverter customerConverter;
    @Test
    void convertCustomer_shouldReturnCustomerDto() {
        Credit credit = Credit.builder()
                .id(UUID.randomUUID())
                .build();
        Customer customer = Customer.builder()
                .id(UUID.randomUUID())
                .name("name")
                .surname("surname")
                .identityNumber("2222222222")
                .callNumber("05059656565")
                .birthDay(new GregorianCalendar(1998, 3, 30).getTime())
                .salary(5000)
                .assurance(10000)
                .creditScore(1000)
                .credit(credit)
                .build();


        CustomerDto result= customerConverter.convert(customer);

        assertEquals(customer.getId(),result.getId());
        assertEquals(customer.getName(),result.getName());
        assertEquals(customer.getSurname(),result.getSurname());
        assertEquals(customer.getIdentityNumber(),result.getIdentityNumber());
        assertEquals(customer.getCallNumber(),result.getCallNumber());
        assertEquals(customer.getBirthDay(),result.getBirthDay());
        assertEquals(customer.getSalary(),result.getSalary());
        assertEquals(customer.getAssurance(),result.getAssurance());
        assertEquals(customer.getCreditScore(),result.getCreditScore());
        assertEquals(customer.getCredit().getId(),result.getCreditId());


    }


}