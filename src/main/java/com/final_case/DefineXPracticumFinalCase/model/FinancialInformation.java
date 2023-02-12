package com.final_case.DefineXPracticumFinalCase.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "financial_information")
@Entity
public class FinancialInformation {

    @Id
    @GeneratedValue()
    private UUID id;

    @NotNull(message = "Salary is mandatory")
    @Column(name = "salary",nullable = false)
    private double salary;

    private double assurance;

    @Column(name = "credit_score",nullable = false)
    private double creditScore;
    private double creditLimit;
    private boolean isAcceptedCredit;
    @OneToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
