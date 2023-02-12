package com.final_case.DefineXPracticumFinalCase.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "customer")
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String surname;
    private String identityNumber;
    private double salary;
    private String callNumber;
    private LocalDate birthDay;
    private double assurance;
    private double creditScore;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "customer")
//    @JoinColumn(name = "credit_application_id")
    private CreditApplication creditApplication;


}
