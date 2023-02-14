package com.final_case.DefineXPracticumFinalCase.model;


import lombok.*;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @NotNull(message = "Salary is mandatory")
    @Column(name = "salary",nullable = false)
    private double salary;

    private double assurance;

    @Column(name = "credit_score",nullable = false)
    private double creditScore;


    @OneToOne()
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer;

}
