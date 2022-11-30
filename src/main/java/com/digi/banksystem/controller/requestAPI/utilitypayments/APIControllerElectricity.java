package com.digi.banksystem.controller.requestAPI.utilitypayments;

import com.digi.banksystem.exceptions.OperationException;
import com.digi.banksystem.service.APIservice.APIServiceElectricity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/API-electricity")
public class APIControllerElectricity {

    @Autowired
    private APIServiceElectricity apiServiceElectricity;

    @GetMapping("/get-by-social-number")
    public ResponseEntity<?> getElPaymentsBySocialNumber(@RequestParam long socialNumber) {

        return ResponseEntity.ok(apiServiceElectricity.getElPaymentsBySocialNumber(socialNumber));
    }

    @PatchMapping("/pay-from-user-account")
    public ResponseEntity<?> pay(@RequestParam long socialNumber, @RequestParam int paymentAmount) throws OperationException {


        return ResponseEntity.ok(apiServiceElectricity.pay(socialNumber, paymentAmount));
    }
}
