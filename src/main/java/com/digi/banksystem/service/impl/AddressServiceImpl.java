package com.digi.banksystem.service.impl;

import com.digi.banksystem.exceptions.NotFoundException;
import com.digi.banksystem.model.Address;
import com.digi.banksystem.model.User;
import com.digi.banksystem.model.requestdto.AddressDTO;
import com.digi.banksystem.repository.AddressRepository;
import com.digi.banksystem.repository.UserRepository;
import com.digi.banksystem.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {


    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(AddressDTO addressDTO, String email) {
        Address address = new Address();
        User user = userRepository.getByEmail(email);
        address.setCountry(addressDTO.getCountry());
        address.setCity(addressDTO.getCity());
        address.setStreet(addressDTO.getStreet());
        address.setHome(addressDTO.getHome());
        address.setUser(user);
        addressRepository.save(address);

    }

    @Override
    public Address getAddressById(int id) throws NotFoundException {
        Optional<Address> addresses = addressRepository.findById(id);
        if (addresses.isEmpty()) {
            throw new NotFoundException("address not found with given ID");
        }
        return addresses.get();
    }
}
