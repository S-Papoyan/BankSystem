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

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "SELECT * FROM account where user_id = ?")
    List<Account> getAccountsByUserId(int id);

}
/*
name
email
subject
comment

 */