package com.digi.banksystem.service.impl;

import com.digi.banksystem.model.Loan;
import com.digi.banksystem.model.User;
import com.digi.banksystem.model.enums.Currency;
import com.digi.banksystem.model.requestdto.LoanDTO;
import com.digi.banksystem.repository.LoanRepository;
import com.digi.banksystem.repository.UserRepository;
import com.digi.banksystem.service.LoanService;
import com.digi.banksystem.util.GenerateToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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
        loan.setInterestRate(loanDTO.getInterestRate());
        loan.setBalance(loanDTO.getLoanAmount());
        loan.setInterestToBePaid(0.0);
        calendar.add(Calendar.MONTH, -loanDTO.getPeriod() + 1);
        loan.setNextPaymentDate(calendar.getTime());
        loan.setNextPaymentAmount(((loanDTO.getLoanAmount() * loanDTO.getInterestRate() / 100) / 12) +
                (loanDTO.getLoanAmount() / loanDTO.getPeriod()));
        loan.setUser(user);
        loanRepository.save(loan);
    }

//    @Override
//    public Loan getLoanInfo(String email) {
//        User user = userRepository.getByEmail(email);
//        List<Loan> loans = loanRepository.getLoanInfo(user.getId());
//        for (int i = 0; i < loans.size(); i++) {
//            Loan loan = loans.get(i);
//            loan.setInterestToBePaid((loan.getBalance()* loan.getInterestRate()/100)/365);
//        }


//    }
}
