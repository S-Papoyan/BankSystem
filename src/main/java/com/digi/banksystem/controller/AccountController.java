package com.digi.banksystem.controller;

import com.digi.banksystem.exceptions.NotFoundException;
import com.digi.banksystem.exceptions.OperationException;
import com.digi.banksystem.model.responsedto.AccountResponseDTO;
import com.digi.banksystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @PostMapping("/create-AMD-current-account")
    public ResponseEntity<?> createAMDAccount(Principal principal) {
        accountService.createAMDCurrentAccount(principal.getName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PostMapping("/create-USD-current-account")
    public ResponseEntity<?> createUSDAccount(Principal principal) {
        accountService.createUSDCurrentAccount(principal.getName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/get-accounts-by-id")
    public ResponseEntity<?> getByUserId(@RequestParam int id) throws NotFoundException {
        List<AccountResponseDTO> byUserID = accountService.getByUserID(id);
        return ResponseEntity.ok(byUserID);
    }


    @GetMapping("/get-account-by-account-number")
    public ResponseEntity<?> getByAccountNumber(@RequestParam long accountNumber) throws NotFoundException {
        return ResponseEntity.ok(accountService.getByAccountNumber(accountNumber));
    }


    @PostMapping("/cash-in")
    public ResponseEntity<?> accountCreditingInCash(@RequestParam long accountNumber, @RequestParam double amount) {
        accountService.accountCreditingInCash(accountNumber, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/cash-out")
    public ResponseEntity<?> cashWithdrawal(@RequestParam long accountNumber, @RequestParam double amount) throws OperationException {
        accountService.cashWithdrawal(accountNumber, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transfersBetweenAccounts(@RequestParam long accountNumberFrom,
                                                      @RequestParam long accountNumberTo,
                                                      @RequestParam double amount) throws OperationException, NotFoundException {
        accountService.transfersBetweenAccounts(accountNumberFrom, accountNumberTo, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/change-USD-to-AMD")
    public ResponseEntity<?> changeUSDtoAMD(@RequestParam long accountNumberFrom,
                                            @RequestParam long accountNumberTo,
                                            @RequestParam double amount,
                                            @RequestParam double exchangeRate) throws OperationException, NotFoundException {
        accountService.changeUSDtoAMD(accountNumberFrom, accountNumberTo, amount, exchangeRate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/change-AMD-to-USD")
    public ResponseEntity<?> changeAMDtoUSD(@RequestParam long accountNumberFrom,
                                            @RequestParam long accountNumberTo,
                                            @RequestParam double amount,
                                            @RequestParam double exchangeRate) throws OperationException, NotFoundException {
        accountService.changeAMDtoUSD(accountNumberFrom, accountNumberTo, amount, exchangeRate);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
