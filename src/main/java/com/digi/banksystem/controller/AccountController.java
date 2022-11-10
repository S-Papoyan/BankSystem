package com.digi.banksystem.controller;


import com.digi.banksystem.exceptions.OperationException;
import com.digi.banksystem.model.Account;
import com.digi.banksystem.model.User;
import com.digi.banksystem.service.AccountService;
import com.digi.banksystem.service.UserService;
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

    @Autowired
    private UserService userService;


    @PostMapping("/create-AMD-current-account")
    public ResponseEntity<?> createAMDAccount(Principal principal) {
        accountService.createAMDCurrentAccount(principal.getName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/get-all-accounts")
    public ResponseEntity<?> getByUserID(Principal principal) {
        String email = principal.getName();
        User user = userService.getByEmail(email);
        List<Account> byUserId = accountService.getByUserId(user.getId());
        return ResponseEntity.ok(byUserId);
    }

    @PostMapping("/cash-in")
    public ResponseEntity<?> accountCreditingInCash(Principal principal, @RequestParam Double amount) {
        String email = principal.getName();
        accountService.accountCreditingInCash(email, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/cash-out")
    public ResponseEntity<?> cashWithdrawal(Principal principal, @RequestParam Double amount) throws OperationException {
        String email = principal.getName();
        accountService.cashWithdrawal(email, amount);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
