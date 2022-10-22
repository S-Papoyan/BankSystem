package com.digi.banksystem.controller;

import com.digi.banksystem.model.requestdto.UserDTO;
import com.digi.banksystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;


    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        service.create(userDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
