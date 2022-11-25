package com.digi.banksystem.service;

import com.digi.banksystem.exceptions.NotFoundException;
import com.digi.banksystem.exceptions.OperationException;
import com.digi.banksystem.model.Account;
import com.digi.banksystem.model.responsedto.AccountResponseDTO;

import java.util.List;

public interface AccountService {

    void createAMDCurrentAccount(String email);

    void createUSDCurrentAccount(String email);

    List<AccountResponseDTO> getByUserID(int id) throws NotFoundException;

    AccountResponseDTO getByAccountNumber(long accountNumber) throws NotFoundException;

    void accountCreditingInCash(long accountNumber, double amount);

    void cashWithdrawal(long accountNumber, double amount) throws OperationException;

    void transfersBetweenAccounts(long accountNumberFrom, long accountNumberTo, double amount) throws OperationException, NotFoundException;

    void changeUSDtoAMD(long accountNumberFrom, long accountNumberTo, double amount, double exchangeRate) throws NotFoundException, OperationException;

    void changeAMDtoUSD(long accountNumberFrom, long accountNumberTo, double amount, double exchangeRate) throws NotFoundException, OperationException;


}
