package com.digi.banksystem.controller;

import com.digi.banksystem.exceptions.NotFoundException;
import com.digi.banksystem.model.requestdto.LoanDTO;
import com.digi.banksystem.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/loan")
public class LoanController {


    @Autowired
    private LoanService loanService;

    @PostMapping("/new_AMD_loan")
    public ResponseEntity<?> newAMDLoan(Principal principal, @RequestBody LoanDTO loanDTO) {
        String email = principal.getName();
        loanService.newAMDLoan(email, loanDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get-by-user-id")
    public ResponseEntity<?> getLoansByUserId(Principal principal) throws NotFoundException {
        String email = principal.getName();
        return ResponseEntity.ok(loanService.getLoansByUserId(email));
    }

    @PatchMapping("/updatePercent")
    public ResponseEntity<?> updatePercent(Principal principal) {
        String email = principal.getName();
        loanService.updatePercent(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/pay-loan")
    public ResponseEntity<?> pay(Principal principal, @RequestParam long contractNumber, @RequestParam double amountOfPay) {
        String email = principal.getName();
        loanService.pay(email, contractNumber, amountOfPay);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
