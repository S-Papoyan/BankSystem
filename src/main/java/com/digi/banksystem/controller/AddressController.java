package com.digi.banksystem.controller;

import com.digi.banksystem.model.requestdto.AddressDTO;
import com.digi.banksystem.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/create")
    public ResponseEntity<?> saveAddress(@RequestBody AddressDTO dto, Principal principal) {
        addressService.save(dto, principal.getName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
