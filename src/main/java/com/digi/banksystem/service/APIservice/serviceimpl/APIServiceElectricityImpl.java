package com.digi.banksystem.service.APIservice.serviceimpl;

import com.digi.banksystem.model.API.ElectricityAPI;
import com.digi.banksystem.service.APIservice.APIServiceElectricity;
import com.digi.banksystem.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class APIServiceElectricityImpl implements APIServiceElectricity {

    private AccountService accountService;

    public static final String URL = "http://localhost:8081/electricity/";

    @Override
    public ElectricityAPI getElPaymentsBySocialNumber(long socialNumber) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(URL + "get-by-social-number?socialNumber=" + socialNumber, ElectricityAPI.class);
    }

    @Override
    public ElectricityAPI pay(long socialNumber, int paymentAmount) {
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(URL + "pay?socialNumber=" + socialNumber + "&paymentAmount=" + paymentAmount, ElectricityAPI.class);

    }
}
