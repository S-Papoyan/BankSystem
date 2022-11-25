package com.digi.banksystem.repository;

import com.digi.banksystem.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {


    @Query(nativeQuery = true, value = "SELECT * FROM account where user_id = ?")
    List<Account> getAccountsByUserId(int id);

    @Query(nativeQuery = true, value = "SELECT * FROM account where account_number = ?")
    Account getByAccountNumber (long accountNumber);

}
