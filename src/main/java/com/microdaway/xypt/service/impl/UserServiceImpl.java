package com.microdaway.xypt.service.impl;

import com.microdaway.xypt.entity.Money;
import com.microdaway.xypt.entity.User;
import com.microdaway.xypt.mapper.UserMapper;
import com.microdaway.xypt.service.UserService;
import com.microdaway.xypt.utils.Md5Util;
import com.microdaway.xypt.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    //根据用户名查找用户
    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    //注册
    @Override
    public void register(String username, String password) {
        //对密码进行加密
        String md5Password = Md5Util.getMD5String(password);
        userMapper.register(username, md5Password);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public BigDecimal balance() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        return userMapper.balance(userId);
    }

    @Override
    public void payUpdate(BigDecimal money, BigDecimal newMoney) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        userMapper.payUpdate(userId, newMoney);
    }

    @Override
    public void payInsert(BigDecimal money, BigDecimal newMoney) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        userMapper.payInsert(userId, money);
    }

    @Override
    public List<Money> incomeRecord() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Money> list = userMapper.incomeRecord(userId);
        return list;
    }
}
