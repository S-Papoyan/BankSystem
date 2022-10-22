package com.digi.banksystem.service.impl;

import com.digi.banksystem.model.User;
import com.digi.banksystem.model.enums.Status;
import com.digi.banksystem.model.requestdto.UserDTO;
import com.digi.banksystem.repository.UserRepository;
import com.digi.banksystem.service.UserService;
import com.digi.banksystem.util.EmailUtil;
import com.digi.banksystem.util.GenerateToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailUtil emailUtil;

    @Override
    public void create(UserDTO userDTO) {
        User user = new User();
        user.setId(0);
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setYear(userDTO.getYear());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        String verifyCode = GenerateToken.generateVerifyCode();
        user.setVerify(verifyCode);
        user.setStatus(Status.INACTIVE);
        emailUtil.sendEmail(userDTO.getEmail(), "your verify code", verifyCode);
        userRepository.save(user);
    }


    @Override
    public User getByEmail(String email) {
        User user = userRepository.getByEmail(email);
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException("not fount");
    }
}
