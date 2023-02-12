package com.final_case.DefineXPracticumFinalCase.repository;

import com.final_case.DefineXPracticumFinalCase.model.FinancialInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FinancialInformationRepository extends JpaRepository<FinancialInformation, UUID> {
}
