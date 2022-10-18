package com.digi.banksystem.controller;

import com.digi.banksystem.model.requestdto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO){

        return new ResponseEntity<>(HttpStatus.CREATED);
    }



}
