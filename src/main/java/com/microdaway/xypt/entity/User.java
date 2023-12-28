package com.microdaway.xypt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class User {
    private Integer id; //id
    private String username; //用户名
    @JsonIgnore
    private String password; //密码
    @Pattern(regexp = "^\\S{1,15}$")
    private String nickname; //昵称
    @URL
    private String avatar; //头像
    private BigDecimal accountMoney; //账户金额
    private Integer role; //角色 0 管理员 1 普通用户 2 骑手
    private Integer gender; //性别 0 女 1 男
    @Pattern(regexp = "^1[3-9]\\d{9}$")
    private String phone; //手机号
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间
}
