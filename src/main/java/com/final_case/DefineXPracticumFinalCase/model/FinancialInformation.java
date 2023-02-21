package com.final_case.DefineXPracticumFinalCase.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;


import java.util.UUID;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@Table(name = "financial_information")
@Entity
public class FinancialInformation extends Auditable{

    @Id
    @GeneratedValue()
    private UUID id;

    @Range(min = 0)
    @Column(name = "salary",nullable = false)
    private double salary;

    private double assurance;

    @Column(name = "credit_score",nullable = false)
    private double creditScore;


    @OneToOne()
    @JoinColumn(name = "customer_id",nullable = false)
    @MapsId
    private Customer customer;

}
