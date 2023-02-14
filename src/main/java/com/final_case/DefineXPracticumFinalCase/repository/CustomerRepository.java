package com.final_case.DefineXPracticumFinalCase.repository;

import com.final_case.DefineXPracticumFinalCase.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    Optional<Customer> findByIdentityNumberAndBirthDay(String identityNumber, LocalDate bithDay);

}
