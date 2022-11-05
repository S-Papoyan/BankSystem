package com.digi.banksystem.service;

import com.digi.banksystem.exceptions.NotFoundException;
import com.digi.banksystem.model.Address;
import com.digi.banksystem.model.requestdto.AddressDTO;

public interface AddressService {

    void save(AddressDTO addressDTO, String email);

    Address getAddressById(int id) throws NotFoundException;

}
