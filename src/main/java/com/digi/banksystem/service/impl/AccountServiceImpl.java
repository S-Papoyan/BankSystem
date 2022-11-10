package com.digi.banksystem.service.impl;

import com.digi.banksystem.exceptions.ErrorMessages;
import com.digi.banksystem.exceptions.OperationException;
import com.digi.banksystem.model.Account;
import com.digi.banksystem.model.User;
import com.digi.banksystem.model.enums.Status;
import com.digi.banksystem.repository.AccountRepository;
import com.digi.banksystem.repository.UserRepository;
import com.digi.banksystem.service.AccountService;
import com.digi.banksystem.util.GenerateToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void createAMDCurrentAccount(String email) {
        User user = userRepository.getByEmail(email);
        Account account = new Account();
        account.setId(0);
        account.setAccountName("Current Account");
        account.setCurrency("AMD");
        account.setAccountNumber(Long.parseLong(GenerateToken.generateAccountNumber()));
        account.setBalance(0.0);
        account.setStatus(Status.INACTIVE);
        account.setUser(user);
        accountRepository.save(account);
    }

    @Override
    public List<Account> getByUserId(int id) {
        return accountRepository.getAccountsByUserId(id);
    }

    @Override
    public void accountCreditingInCash(String email, Double amount) {
        User user = userRepository.getByEmail(email);
        Account account = accountRepository.getAccountsByUserId(user.getId()).get(0);
        if (account.getBalance() == 0.0) {
            account.setStatus(Status.ACTIVE);
        }
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    @Override
    public void cashWithdrawal(String email, Double amount) throws OperationException {
        User user = userRepository.getByEmail(email);
        Account account = accountRepository.getAccountsByUserId(user.getId()).get(0);
        if (amount > account.getBalance()) {
            throw new OperationException(ErrorMessages.OPERATION_EXCEPTION);
        }
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
    }
}
