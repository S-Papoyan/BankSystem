package com.digi.banksystem.model.requestdto;

import lombok.Data;

@Data
public class UserDTO {

    private String name;
    private String surname;
    private int year;
    private String email;
    private String password;

}
