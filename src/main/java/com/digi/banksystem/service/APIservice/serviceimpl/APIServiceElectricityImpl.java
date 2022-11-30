package com.digi.banksystem.service.APIservice.serviceimpl;

import com.digi.banksystem.model.API.ElectricityAPI;
import com.digi.banksystem.service.APIservice.APIServiceElectricity;
import com.digi.banksystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class APIServiceElectricityImpl implements APIServiceElectricity {

    @Autowired
    private AccountService accountService;

    public static final String URL = "http://localhost:8081/electricity/";

    @Override
    public ElectricityAPI getElPaymentsBySocialNumber(long socialNumber) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(URL + "get-by-social-number?socialNumber=" + socialNumber, ElectricityAPI.class);
    }

    @Override
    public ElectricityAPI pay(long socialNumber, int paymentAmount) {
        HttpHeaders httpHeaders = new HttpHeaders();


        String s = "{\n" +
                "    \"socialNumber\":" + socialNumber + "\n" +
                "    \"paymentAmount\":" + paymentAmount + "\n" +
                "    \n" +
                "}";
        HttpEntity<String> entity = new HttpEntity<>(s, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.patchForObject(URL + "pay", entity, ElectricityAPI.class);

//        return restTemplate.getForObject(URL + "pay?socialNumber=" + socialNumber + "&paymentAmount=" + paymentAmount, ElectricityAPI.class);

    }
}
