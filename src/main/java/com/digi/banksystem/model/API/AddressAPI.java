package com.digi.banksystem.model.API;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressAPI {


    private int id;
    private String country;
    private String city;
    private String street;
    private String home;
    private int apartment;
    private UserAPI user;
}
