package com.digi.banksystem.service;

import com.digi.banksystem.exceptions.OperationException;
import com.digi.banksystem.model.Account;

import java.util.List;

public interface AccountService {

    void createAMDCurrentAccount(String email);

    List<Account> getByUserId(int id);

    void accountCreditingInCash(String email, Double amount);

    void cashWithdrawal(String email, Double amount) throws OperationException;

}
