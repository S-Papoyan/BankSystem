package com.digi.banksystem.service.impl;

import com.digi.banksystem.model.User;
import com.digi.banksystem.model.requestdto.UserDTO;
import com.digi.banksystem.repository.UserRepository;
import com.digi.banksystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void create(UserDTO userDTO) {

    }

    @Override
    public User getByEmail(String email) {
        User user = userRepository.getByEmail(email);
        if (user != null) {
            return user;
        }
        throw new RuntimeException("not fount");
    }
}
