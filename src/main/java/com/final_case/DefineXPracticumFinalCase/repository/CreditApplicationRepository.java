package com.final_case.DefineXPracticumFinalCase.repository;

import com.final_case.DefineXPracticumFinalCase.model.CreditApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CreditApplicationRepository extends JpaRepository<CreditApplication, UUID> {
}
