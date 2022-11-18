package com.digi.banksystem.service.APIservice;

import com.digi.banksystem.exceptions.OperationException;
import com.digi.banksystem.model.API.ElectricityAPI;

public interface APIServiceElectricity {

    ElectricityAPI getElPaymentsBySocialNumber(long socialNumber);

    ElectricityAPI pay(long socialNumber, double paymentAmount) throws OperationException;
}
