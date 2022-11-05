package com.digi.banksystem.controller;

import com.digi.banksystem.exceptions.BadRequest;
import com.digi.banksystem.exceptions.NotFoundException;
import com.digi.banksystem.exceptions.ValidationException;
import com.digi.banksystem.model.User;
import com.digi.banksystem.model.requestdto.UserDTO;
import com.digi.banksystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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

    @PatchMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestParam String email, @RequestParam String verifyCode) throws NotFoundException {
        service.verifyUser(email, verifyCode);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestParam String oldPassword,
                                            @RequestParam String newPassword,
                                            @RequestParam String confirmPassword,
                                            Principal principal) throws ValidationException, NotFoundException {
        String email = principal.getName();
        service.changePassword(email, oldPassword, newPassword, confirmPassword);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestParam int id, @RequestBody UserDTO userDTO) throws BadRequest, NotFoundException {
        service.updateUser(id, userDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-email")
    public ResponseEntity<?> getEmail(@RequestParam String email) throws NotFoundException {
        service.getEmail(email);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-token")
    public ResponseEntity<?> getToken(@RequestParam String email, @RequestParam String token) throws ValidationException {

        return ResponseEntity.ok(service.getToken(email, token));
    }

    @PatchMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String email,
                                            @RequestParam String newPassword,
                                            @RequestParam String confirmPassword) throws ValidationException {
        service.forgotPassword(email, newPassword, confirmPassword);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) throws NotFoundException {
        return ResponseEntity.ok(service.getUser(id));
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }
}






