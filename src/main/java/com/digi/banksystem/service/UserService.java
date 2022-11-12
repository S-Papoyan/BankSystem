package com.digi.banksystem.service;

import com.digi.banksystem.exceptions.BadRequest;
import com.digi.banksystem.exceptions.NotFoundException;
import com.digi.banksystem.exceptions.ValidationException;
import com.digi.banksystem.model.User;
import com.digi.banksystem.model.requestdto.UserDTO;

import java.util.List;

public interface UserService {

    void create(UserDTO userDTO);

    User getByEmail(String email);

    void verifyUser(String email, String code) throws NotFoundException;

    void changePassword(String email, String oldPassword, String newPassword, String confirmPassword) throws ValidationException, NotFoundException;

    void updateUser(Integer id, UserDTO userDTO) throws BadRequest, NotFoundException;

    void getEmail(String email) throws NotFoundException;

    Boolean getToken(String email, String token) throws ValidationException;

    void forgotPassword(String email, String newPassword, String confirmPassword) throws ValidationException;

    User getUser(int id) throws NotFoundException;

    List<?> getAllUsers();

    List<User> searchUser(String name);
}
