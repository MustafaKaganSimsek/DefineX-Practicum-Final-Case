package com.final_case.DefineXPracticumFinalCase.model;

import com.final_case.DefineXPracticumFinalCase.enumeration.CreditMessage;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;



import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@Table(name = "credit")
@Entity
public class Credit extends Auditable{

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "credit_limit",nullable = false)
    private double creditLimit;

    @NotNull(message = "Name is mandatory")
    @Column(name = "isAccepted",nullable = false)
    private boolean isAccepted;

    @Enumerated(EnumType.STRING)
    @Column(name = "message",nullable = false)
    private CreditMessage message;

    @OneToOne()
    @JoinColumn(name = "customer_id",nullable = false)
    @MapsId
    private Customer customer;
}
