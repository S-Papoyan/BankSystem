package com.digi.banksystem.controller;

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

//    public ResponseEntity<?> getLoanInfo(Principal principal){
//        String email = principal.getName();
//
//    }
}
