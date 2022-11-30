package com.digi.banksystem.model.responsedto;


import com.digi.banksystem.model.enums.Currency;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Calendar;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanResponseDTO {

    @JsonIgnore
    private int id;

    @JsonProperty("loanName")
    private String loanName;

    @JsonProperty("loanAmount")
    private double loanAmount;

    @JsonProperty("currency")
    private Currency currency;

    @JsonProperty("contractNumber")
    private long contractNumber;

    @JsonProperty("openDate")
    private String openDate;

    @JsonProperty("period")
    private int period;

    @JsonProperty("endDate")
    private String endDate;

    @JsonProperty("interestRate")
    private double interestRate;

    @JsonProperty("balance")
    private double balance;

    @JsonProperty("lastPaymentDate")
    private Calendar lastPaymentDate;

    @JsonProperty("interestToBePaid")
    private double interestToBePaid;

    @JsonProperty("nextPaymentDate")
    private String nextPaymentDate;

    @JsonProperty("nextPaymentAmount")
    private double nextPaymentAmount;

    @JsonProperty("user")
    private UserResponseDTO userResponseDTO;
}
