package com.digi.banksystem.service.impl;

import com.digi.banksystem.exceptions.NotFoundException;
import com.digi.banksystem.model.Loan;
import com.digi.banksystem.model.User;
import com.digi.banksystem.model.enums.Currency;
import com.digi.banksystem.model.requestdto.LoanDTO;
import com.digi.banksystem.model.responsedto.LoanResponseDTO;
import com.digi.banksystem.repository.LoanRepository;
import com.digi.banksystem.repository.UserRepository;
import com.digi.banksystem.service.LoanService;
import com.digi.banksystem.util.GenerateToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void newAMDLoan(String email, LoanDTO loanDTO) {
        User user = userRepository.getByEmail(email);
        Loan loan = new Loan();
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d-MMMM-yyyy");
        loan.setId(0);
        loan.setLoanName(loanDTO.getLoanName());
        loan.setLoanAmount(loanDTO.getLoanAmount());
        loan.setCurrency(Currency.AMD);
        loan.setContractNumber(Long.parseLong(GenerateToken.generateLoanContractNumber()));
        loan.setOpenDate(dateFormat.format(calendar.getTime()));
        loan.setPeriod(loanDTO.getPeriod());
        calendar.add(Calendar.MONTH, loanDTO.getPeriod());
        loan.setEndDate(dateFormat.format(calendar.getTime()));
        calendar.add(Calendar.MONTH, -loanDTO.getPeriod());
        loan.setInterestRate(loanDTO.getInterestRate());
        loan.setBalance(loanDTO.getLoanAmount());
        loan.setLastPaymentDate(new GregorianCalendar());
        loan.setInterestToBePaid(0.0);
        calendar.add(Calendar.MONTH, 1);
        loan.setNextPaymentDate(dateFormat.format(calendar.getTime()));
        loan.setNextPaymentAmount(((loanDTO.getLoanAmount() * loanDTO.getInterestRate() / 100) / 12) +
                (loanDTO.getLoanAmount() / loanDTO.getPeriod()));
        loan.setUser(user);
        loanRepository.save(loan);
    }

    @Override
    public void updatePercent(String email) {
        Calendar today = new GregorianCalendar();
        User user = userRepository.getByEmail(email);
        List<Loan> loans = loanRepository.getLoanInfo(user.getId());
        for (Loan loan : loans) {
            loan.setInterestToBePaid(((loan.getBalance() * loan.getInterestRate() / 100) / 365) *
                    (today.get(Calendar.DAY_OF_YEAR) - loan.getLastPaymentDate().get(Calendar.DAY_OF_YEAR)));
            loanRepository.save(loan);
        }
    }

    @Override
    public List<LoanResponseDTO> getLoansByUserId(String email) throws NotFoundException {
        updatePercent(email);
        User user = userRepository.getByEmail(email);
        List<Loan> loans = loanRepository.getLoanInfo(user.getId());
        ObjectMapper objectMapper = new ObjectMapper();
        List<LoanResponseDTO> loanResponseDTO = new ArrayList<>();
        if (loans.isEmpty()) {
            throw new NotFoundException("loans not found with given ID");
        }
        for (Loan loan : loans) {
            loanResponseDTO.add(objectMapper.convertValue(loan, LoanResponseDTO.class));
        }
        return loanResponseDTO;
    }


    @Override
    public void pay(String email, long contractNumber, double amountOfPay) {
        updatePercent(email);
        Loan loan = loanRepository.getByContractNumber(contractNumber);
        if (amountOfPay >= loan.getInterestToBePaid()) {
            loan.setBalance(loan.getBalance() - (amountOfPay - loan.getInterestToBePaid()));
            loan.setInterestToBePaid(0.0);
        } else {
            loan.setInterestToBePaid(loan.getInterestToBePaid() - amountOfPay);
        }
        loan.setLastPaymentDate(new GregorianCalendar());
        loanRepository.save(loan);
    }
}



