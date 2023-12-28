package com.microdaway.xypt.service;

import com.microdaway.xypt.entity.Money;
import com.microdaway.xypt.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {
    User findByUsername(String username);

    void register(String username, String password);

    void update(User user);

    BigDecimal balance();

    void payUpdate(BigDecimal money, BigDecimal newMoney);

    void payInsert(BigDecimal money, BigDecimal newMoney);

    List<Money> incomeRecord();
}
