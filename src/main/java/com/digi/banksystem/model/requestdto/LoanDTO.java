package com.digi.banksystem.model.requestdto;

import lombok.Data;
import lombok.Getter;

import java.util.Calendar;
@Data
public class LoanDTO {

    private String loanName;
    private double loanAmount;
    private int period;
    private double interestRate;

}
