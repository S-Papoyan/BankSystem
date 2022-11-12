package com.digi.banksystem.service;

import com.digi.banksystem.exceptions.NotFoundException;
import com.digi.banksystem.exceptions.OperationException;
import com.digi.banksystem.model.Account;
import com.digi.banksystem.model.responsedto.AccountResponseDTO;

import java.util.List;

public interface AccountService {

    void createAMDCurrentAccount(String email);

    AccountResponseDTO getByUserId(int id) throws NotFoundException;

    void accountCreditingInCash(String email, Double amount);

    void cashWithdrawal(String email, Double amount) throws OperationException;

}
