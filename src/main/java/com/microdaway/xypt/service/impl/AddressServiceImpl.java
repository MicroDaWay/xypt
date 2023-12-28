package com.microdaway.xypt.service.impl;

import com.microdaway.xypt.entity.Address;
import com.microdaway.xypt.mapper.AddressMapper;
import com.microdaway.xypt.service.AddressService;
import com.microdaway.xypt.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<Address> address() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Address> addressList = addressMapper.address(userId);
        return addressList;
    }

    @Override
    public void resetDefault() {
        addressMapper.resetDefault();
    }

    @Override
    public void addAddress(Address address) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        address.setUserId(userId);
        addressMapper.addAddress(address);
    }

    @Override
    public Address getAddressById(Integer id) {
        Address address = addressMapper.getAddressById(id);
        return address;
    }

    @Override
    public void updateAddress(Address address, Integer id) {
        address.setId(id);
        addressMapper.updateAddress(address);
    }

    @Override
    public void deleteAddress(Integer id) {
        addressMapper.deleteAddress(id);
    }
}
