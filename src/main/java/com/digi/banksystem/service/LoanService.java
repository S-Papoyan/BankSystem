package com.digi.banksystem.service;

import com.digi.banksystem.model.Loan;
import com.digi.banksystem.model.requestdto.LoanDTO;

public interface LoanService {

    void newAMDLoan(String email, LoanDTO loanDTO);

//    Loan getLoanInfo(String email);


}
