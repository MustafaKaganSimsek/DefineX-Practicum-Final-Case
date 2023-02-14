package com.final_case.DefineXPracticumFinalCase.model;

import lombok.*;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    private String name;

    private String surname;

    private String identityNumber;



    private String callNumber;

    private LocalDate birthDay;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "customer")
    private FinancialInformation financialInformation;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "customer")
    private Credit credit;

}
