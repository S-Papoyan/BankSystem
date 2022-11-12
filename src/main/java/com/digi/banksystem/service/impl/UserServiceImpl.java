package com.digi.banksystem.service.impl;

import com.digi.banksystem.exceptions.BadRequest;
import com.digi.banksystem.exceptions.ErrorMessages;
import com.digi.banksystem.exceptions.NotFoundException;
import com.digi.banksystem.exceptions.ValidationException;
import com.digi.banksystem.model.User;
import com.digi.banksystem.model.enums.Status;
import com.digi.banksystem.model.requestdto.UserDTO;
import com.digi.banksystem.model.responsedto.UserResponseDTO;
import com.digi.banksystem.repository.UserRepository;
import com.digi.banksystem.service.UserService;
import com.digi.banksystem.util.EmailUtil;
import com.digi.banksystem.util.GenerateToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public void verifyUser(String email, String code) throws NotFoundException {
        User user = userRepository.getByEmail(email);
        if (user == null) {
            throw new NotFoundException(ErrorMessages.NOT_FOUND_USER);
        }
        if (user.getVerify().equals(code)) {
            user.setStatus(Status.ACTIVE);
            user.setVerify(null);
            userRepository.save(user);
        }
    }

    @Override
    public void changePassword(String email, String oldPassword,
                               String newPassword, String confirmPassword) throws ValidationException, NotFoundException {
        if (!newPassword.equals(confirmPassword)) {
            throw new ValidationException(ErrorMessages.NOT_MATCH);
        }
        User user = userRepository.getByEmail(email);
        if (!user.getPassword().equals(passwordEncoder.encode(oldPassword))) {
            throw new NotFoundException(ErrorMessages.NOT_FOUND_PASSWORD);

        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public void updateUser(Integer id, UserDTO userDTO) throws BadRequest, NotFoundException {
        if (id == null) {
            throw new BadRequest("User id must not be null");
        }
        Optional<User> user = null;
        try {
            user = userRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        if (user.isEmpty()) {
            throw new NotFoundException("User not found whit given ID");
        }
        User updateUser = user.get();
        updateUser.setName(userDTO.getName() == null ? updateUser.getName() : userDTO.getName());
        updateUser.setSurname(userDTO.getSurname() == null ? updateUser.getSurname() : userDTO.getSurname());
        updateUser.setYear(userDTO.getYear() == 0 ? updateUser.getYear() : userDTO.getYear());
        userRepository.save(updateUser);
    }

    @Override
    public void getEmail(String email) throws NotFoundException {
        User user = userRepository.getByEmail(email);
        if (user == null) {
            throw new NotFoundException(ErrorMessages.NOT_FOUND_USER);
        }
        String token = GenerateToken.generateToken();
        user.setResetToken(token);
        userRepository.save(user);
        emailUtil.sendEmail(email, "your generated token", token);

    }

    @Override
    public Boolean getToken(String email, String token) throws ValidationException {
        User user = userRepository.getByEmail(email);
        if (!user.getResetToken().equals(token)) {
            throw new ValidationException(ErrorMessages.TOKEN_NOT_MATCH);
        }
        return true;
    }

    @Override
    public void forgotPassword(String email, String newPassword, String confirmPassword) throws ValidationException {
        if (!newPassword.equals(confirmPassword)) {
            throw new ValidationException(ErrorMessages.NOT_MATCH);
        }
        User user = userRepository.getByEmail(email);
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        userRepository.save(user);


    }

    @Override
    public User getUser(int id) throws NotFoundException {
        Optional<User> users = userRepository.findById(id);
        if (users.isEmpty()) {
            throw new NotFoundException("user not found with given ID");
        }
        return users.get();
    }

    @Override
    public List<?> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDTO> userResponseDTOS = new ArrayList<>();
        for (User user : users) {
            UserResponseDTO userDTO = new UserResponseDTO();
            userDTO.setName(user.getName());
            userDTO.setSurname(user.getSurname());
            userDTO.setYear(user.getYear());
            userResponseDTOS.add(userDTO);
        }
        return userResponseDTOS;
    }

    @Override
    public List<User> searchUser(String name) {
        return userRepository.searchUser(name);
    }
}
