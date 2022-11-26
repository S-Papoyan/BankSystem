package com.digi.banksystem.service.APIservice;

import com.digi.banksystem.exceptions.OperationException;
import com.digi.banksystem.model.API.ElectricityAPI;

public interface APIServiceElectricity {

    ElectricityAPI getElPaymentsBySocialNumber(long socialNumber);

    Object pay(long socialNumber, int paymentAmount) throws OperationException;
}
