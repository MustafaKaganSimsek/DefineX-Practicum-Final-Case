package com.final_case.DefineXPracticumFinalCase.model;

import com.final_case.DefineXPracticumFinalCase.enumeration.CreditMessage;
import lombok.*;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    private Customer customer;
}
