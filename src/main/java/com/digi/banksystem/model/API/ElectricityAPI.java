package com.digi.banksystem.model.API;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ElectricityAPI {


    private int id;
    private String contractNumber;
    private String reportingMonth;
    private Double totalSum;
    private Double electricityExpense;
    private Long socialNumberOfCustomer;
    private AddressAPI address;
}
