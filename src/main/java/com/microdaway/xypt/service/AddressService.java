package com.microdaway.xypt.service;

import com.microdaway.xypt.entity.Address;

import java.util.List;

public interface AddressService {
    List<Address> address();

    void resetDefault();

    void addAddress(Address address);

    Address getAddressById(Integer id);

    void updateAddress(Address address, Integer id);

    void deleteAddress(Integer id);
}
