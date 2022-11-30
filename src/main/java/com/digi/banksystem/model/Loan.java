package com.digi.banksystem.model;

import com.digi.banksystem.model.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loans_id")
    private int id;

    @Column(name = "loan_name")
    private String loanName;

    @Column(name = "loan_amount")
    private double loanAmount;

    @Enumerated(value = EnumType.STRING)
    private Currency currency;

    @Column(name = "contract_number")
    private long contractNumber;

    @Column(name = "contract_open_date")
    private String openDate;

    @Column(name = "loan_period_month")
    private int period;

    @Column(name = "contract_end_date")
    private String endDate;

    @Column(name = "interest_rate")
    private double interestRate;

    @Column(name = "loan_balance")
    private double balance;

    @Column(name = "last_payment_date")
    private Calendar lastPaymentDate;

    @Column(name = "interest_to_be_paid")
    private double interestToBePaid;

    @Column(name = "next_payment_date")
    private String nextPaymentDate;

    @Column(name = "next_payment_amount")
    private double nextPaymentAmount;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    public void setLoanAmount(double loanAmount) {
        this.loanAmount = Math.ceil(loanAmount * 100) / 100;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = Math.ceil(interestRate * 100) / 100;
    }

    public void setBalance(double balance) {
        this.balance = Math.ceil(balance * 100) / 100;
    }

    public void setInterestToBePaid(double interestToBePaid) {
        this.interestToBePaid = Math.ceil(interestToBePaid * 100) / 100;
    }

    public void setNextPaymentAmount(double nextPaymentAmount) {
        this.nextPaymentAmount = Math.ceil(nextPaymentAmount * 100) / 100;
    }
}
