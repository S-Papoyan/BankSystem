package com.digi.banksystem.service.impl;

import com.digi.banksystem.exceptions.ErrorMessages;
import com.digi.banksystem.exceptions.NotFoundException;
import com.digi.banksystem.exceptions.OperationException;
import com.digi.banksystem.model.Account;
import com.digi.banksystem.model.User;
import com.digi.banksystem.model.enums.AccountName;
import com.digi.banksystem.model.enums.Currency;
import com.digi.banksystem.model.enums.StatusAccount;
import com.digi.banksystem.model.responsedto.AccountResponseDTO;
import com.digi.banksystem.repository.AccountRepository;
import com.digi.banksystem.repository.UserRepository;
import com.digi.banksystem.service.AccountService;
import com.digi.banksystem.util.GenerateToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        account.setAccountName(AccountName.CURRENT_ACCOUNT);
        account.setCurrency(Currency.AMD);
        account.setAccountNumber(Long.parseLong(GenerateToken.generateAccountNumber()));
        account.setBalance(0.0);
        account.setStatus(StatusAccount.INACTIVE);
        account.setUser(user);
        accountRepository.save(account);
    }

    @Override
    public void createUSDCurrentAccount(String email) {
        User user = userRepository.getByEmail(email);
        Account account = new Account();
        account.setId(0);
        account.setAccountName(AccountName.CURRENT_ACCOUNT);
        account.setCurrency(Currency.USD);
        account.setAccountNumber(Long.parseLong(GenerateToken.generateAccountNumber()));
        account.setBalance(0.0);
        account.setStatus(StatusAccount.INACTIVE);
        account.setUser(user);
        accountRepository.save(account);
    }

    @Override
    public List<AccountResponseDTO> getByUserID(int id) throws NotFoundException {
        List<Account> accountsByUserId = accountRepository.getAccountsByUserId(id);
        if (accountsByUserId.isEmpty()) {
            throw new NotFoundException("accounts not found with given ID");
        }
        List<AccountResponseDTO> result = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        for (Account value : accountsByUserId) {
            result.add(objectMapper.convertValue(value, AccountResponseDTO.class));
        }
        return result;
    }

    @Override
    public AccountResponseDTO getByAccountNumber(long accountNumber) throws NotFoundException {
        Account account = accountRepository.getByAccountNumber(accountNumber);
        if (account == null) {
            throw new NotFoundException("accounts not found with given account number");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(account, AccountResponseDTO.class);
    }

    boolean b;

    @Override
    public void accountCreditingInCash(long accountNumber, double amount) {
        Account account = accountRepository.getByAccountNumber(accountNumber);
        if (account.getBalance() == 0 && !b) {
            account.setStatus(StatusAccount.ACTIVE);
            b = true;
        }
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    @Override
    public void cashWithdrawal(long accountNumber, double amount) throws OperationException {
        Account account = accountRepository.getByAccountNumber(accountNumber);
        if (amount > account.getBalance()) {
            throw new OperationException(ErrorMessages.OPERATION_EXCEPTION);
        }
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
    }

    @Override
    public void transfersBetweenAccounts(long accountNumberFrom, long accountNumberTo, double amount) throws OperationException, NotFoundException {
        AccountResponseDTO byAccountNumberFrom = getByAccountNumber(accountNumberFrom);
        AccountResponseDTO byAccountNumberTo = getByAccountNumber(accountNumberTo);
        if (!byAccountNumberFrom.getAccountNumber().equals(byAccountNumberTo.getAccountNumber()) &&
                (byAccountNumberFrom.getCurrency() == Currency.AMD && byAccountNumberTo.getCurrency() == Currency.AMD ||
                        byAccountNumberFrom.getCurrency() == Currency.USD && byAccountNumberTo.getCurrency() == Currency.USD ||
                        byAccountNumberFrom.getCurrency() == Currency.EUR && byAccountNumberTo.getCurrency() == Currency.EUR ||
                        byAccountNumberFrom.getCurrency() == Currency.RUR && byAccountNumberTo.getCurrency() == Currency.RUR)) {
            cashWithdrawal(accountNumberFrom, amount);
            accountCreditingInCash(accountNumberTo, amount);
        } else throw new OperationException(ErrorMessages.TRANSFER_EXCEPTION);
    }

    @Override
    public void changeUSDtoAMD(long accountNumberFrom, long accountNumberTo, double amount, double exchangeRate) throws NotFoundException, OperationException {
        AccountResponseDTO byAccountNumberFrom = getByAccountNumber(accountNumberFrom);
        AccountResponseDTO byAccountNumberTo = getByAccountNumber(accountNumberTo);
        if (byAccountNumberFrom.getCurrency() == Currency.USD && byAccountNumberTo.getCurrency() == Currency.AMD) {
            cashWithdrawal(accountNumberFrom, amount);
            accountCreditingInCash(accountNumberTo, amount * exchangeRate);
        } else throw new OperationException(ErrorMessages.TRANSFER_EXCEPTION);
    }

    @Override
    public void changeAMDtoUSD(long accountNumberFrom, long accountNumberTo, double amount, double exchangeRate) throws NotFoundException, OperationException {
        AccountResponseDTO byAccountNumberFrom = getByAccountNumber(accountNumberFrom);
        AccountResponseDTO byAccountNumberTo = getByAccountNumber(accountNumberTo);
        if (byAccountNumberFrom.getCurrency() == Currency.AMD && byAccountNumberTo.getCurrency() == Currency.USD) {
            cashWithdrawal(accountNumberFrom, amount);
            accountCreditingInCash(accountNumberTo, amount / exchangeRate);
        } else throw new OperationException(ErrorMessages.TRANSFER_EXCEPTION);
    }
}
