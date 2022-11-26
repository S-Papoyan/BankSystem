package com.digi.banksystem.service.APIservice.serviceimpl;

import com.digi.banksystem.model.API.ElectricityAPI;
import com.digi.banksystem.service.APIservice.APIServiceElectricity;
import com.digi.banksystem.service.AccountService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class APIServiceElectricityImpl implements APIServiceElectricity {

    private AccountService accountService;

    public static final String URL = "http://localhost:8081/electricity/pay";

    @Override
    public ElectricityAPI getElPaymentsBySocialNumber(long socialNumber) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(URL + "get-by-social-number?socialNumber=" + socialNumber, ElectricityAPI.class);
    }

    @Override
    public Object pay(long socialNumber, int paymentAmount) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
String s = "{\n" +
        "    \"socialNumber\":" + socialNumber +"\n"+
        "    \"paymentAmount\":" + paymentAmount +"\n"+
        "    \n" +
        "}";

        HttpEntity httpEntity = new HttpEntity(s, headers);

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.patchForObject(URL, httpEntity , Object.class);

    }
}
