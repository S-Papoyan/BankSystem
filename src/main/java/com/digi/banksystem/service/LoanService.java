package com.digi.banksystem.service;

import com.digi.banksystem.exceptions.NotFoundException;
import com.digi.banksystem.model.requestdto.LoanDTO;
import com.digi.banksystem.model.responsedto.LoanResponseDTO;

import java.util.List;

public interface LoanService {

    void newAMDLoan(String email, LoanDTO loanDTO);

    void updatePercent(String email);

    List<LoanResponseDTO> getLoansByUserId(String email) throws NotFoundException;

    void pay(String email, long contractNumber, double amountOfPay);

}
