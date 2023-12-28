package com.microdaway.xypt.controller;

import com.microdaway.xypt.entity.*;
import com.microdaway.xypt.service.UserService;
import com.microdaway.xypt.utils.JwtUtil;
import com.microdaway.xypt.utils.Md5Util;
import com.microdaway.xypt.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //注册
    @PostMapping("/register")
    public Result register(@RequestBody @Validated LoginParams loginParams) {
        String username = loginParams.getUsername();
        String password = loginParams.getPassword();
        User user = userService.findByUsername(username);
        if (user != null) {
            //用户已存在
            return Result.error("用户名已被占用");
        }

        //用户不存在 进行注册
        userService.register(username, password);
        return Result.success();
    }

    //登录
    @PostMapping("/login")
    public Result login(@RequestBody @Validated LoginParams loginParams) {
        String username = loginParams.getUsername();
        String password = loginParams.getPassword();
        User user = userService.findByUsername(username);
        if (user == null) {
            //没找到用户
            return Result.error("用户名错误");
        }

        if (Md5Util.getMD5String(password).equals(user.getPassword())) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", user.getId());
            claims.put("username", user.getUsername());
            String token = JwtUtil.genToken(claims);
            //将token存储到redis中
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(token, token, 24, TimeUnit.HOURS);
            return Result.success(token);
        }

        return Result.error("密码错误");
    }

    //退出登录
    @PostMapping("/logout")
    public Result logout(@RequestHeader(name = "Authorization") String token) {
        //从redis中删除旧的token
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);
        return Result.success();
    }

    //获取用户信息
    @GetMapping("/userInfo")
    public Result<User> userInfo() {
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUsername(username);
        return Result.success(user);
    }

    //修改用户信息
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user) {
        userService.update(user);
        return Result.success();
    }

    //获取用户钱包余额
    @GetMapping("/balance")
    public Result<BigDecimal> balance() {
        BigDecimal bigDecimal = userService.balance();
        return Result.success(bigDecimal);
    }

    //支付
    @PostMapping("/pay")
    public Result pay(@RequestBody Map<String, BigDecimal> params) {
        BigDecimal money = params.get("money");
        BigDecimal oldMoney = userService.balance();
        BigDecimal newMoney = money.add(oldMoney);
        userService.payUpdate(money, newMoney);
        userService.payInsert(money, newMoney);
        return Result.success();
    }

    //获取用户的收支记录
    @GetMapping("/incomeRecord")
    public Result<List<Money>> incomeRecord() {
        List<Money> list = userService.incomeRecord();
        return Result.success(list);
    }
}
