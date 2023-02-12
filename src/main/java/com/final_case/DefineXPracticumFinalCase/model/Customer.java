package com.final_case.DefineXPracticumFinalCase.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @NotBlank(message = "Name is mandatory")
    @Column(name = "name",nullable = false)
    private String name;

    @NotBlank(message = "Surname is mandatory")
    @Column(name = "surname",nullable = false)
    private String surname;

    @NotBlank(message = "Identity number is mandatory")
    @Column(name = "identity_number",nullable = false)
    private String identityNumber;



    @NotBlank(message = "Call number is mandatory")
    @Column(name = "call_number",nullable = false)
    private String callNumber;

    @NotNull(message = "Birth Day is mandatory")
    @Column(name = "birth_day",nullable = false)
    private LocalDate birthDay;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "customer")
    private FinancialInformation financialInformation;


}
