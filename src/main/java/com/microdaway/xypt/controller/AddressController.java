package com.microdaway.xypt.controller;

import com.microdaway.xypt.entity.Address;
import com.microdaway.xypt.entity.Result;
import com.microdaway.xypt.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    //获取用户的所有地址
    @GetMapping("/all")
    public Result<List<Address>> address() {
        List<Address> addressList = addressService.address();
        return Result.success(addressList);
    }

    //将所有地址修改为不是默认地址
    @PutMapping("/resetDefault")
    public Result resetDefault() {
        addressService.resetDefault();
        return Result.success();
    }

    //添加地址
    @PostMapping("/add")
    public Result addAddress(@RequestBody @Validated(Address.Add.class) Address address) {
        if (address.getIsDefault().equals(1)) {
            addressService.resetDefault();
        }
        addressService.addAddress(address);
        return Result.success();
    }

    // 根据id获取地址
    @GetMapping("/{id}")
    public Result<Address> getAddressById(@PathVariable Integer id) {
        Address address = addressService.getAddressById(id);
        return Result.success(address);
    }

    //根据id修改地址
    @PutMapping("/update/{id}")
    public Result updateAddress(@RequestBody Address address, @PathVariable Integer id) {
        if (address.getIsDefault().equals(1)) {
            addressService.resetDefault();
        }
        addressService.updateAddress(address, id);
        return Result.success();
    }

    //根据id删除地址
    @DeleteMapping("/delete/{id}")
    public Result deleteAddress(@PathVariable Integer id) {
        addressService.deleteAddress(id);
        return Result.success();
    }
}
