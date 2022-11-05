package com.digi.banksystem.service;

import com.digi.banksystem.model.requestdto.AddressDTO;

public interface AddressService {

    void save(AddressDTO addressDTO, String email);
}
