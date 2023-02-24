package com.final_case.DefineXPracticumFinalCase.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Range;


import java.time.LocalDate;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@Table(name = "customer")
@Entity
public class Customer extends Auditable{

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

    @NotBlank(message = "Callnumber is mandatory")
    @Column(name = "call_number",nullable = false)
    private String callNumber;

    @NotNull(message = "Birth day is mandatory")
    @Column(name = "birth_day",nullable = false)
    private LocalDate birthDay;

    @Range(min = 0)
    @Column(name = "salary")
    private double salary;

    private double assurance;

    @Column(name = "credit_score")
    private double creditScore;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Credit credit = new Credit();

}
