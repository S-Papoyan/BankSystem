package com.digi.banksystem.service;

import com.digi.banksystem.exceptions.NotFoundException;
import com.digi.banksystem.model.User;
import com.digi.banksystem.model.requestdto.UserDTO;

public interface UserService {

    void create(UserDTO userDTO);
    User getByEmail(String email);
    void verifyUser(String email, String code) throws NotFoundException;

}
