package com.final_case.DefineXPracticumFinalCase.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "credit_application")
@Entity
public class CreditApplication {

    @Id
    @GeneratedValue()
    private UUID id;
    private double creditLimit;
    private boolean isAccepted;
    @OneToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
