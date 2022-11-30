package com.digi.banksystem.repository;

import com.digi.banksystem.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM loans where user_id = ?")
    List<Loan> getLoanInfo(int user_id);

    @Query(nativeQuery = true, value = "SELECT * FROM loans where contract_number = ?")
    Loan getByContractNumber(long contractNumber);


}
