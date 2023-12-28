package com.microdaway.xypt.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.Default;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Address {
    @NotNull(groups = Update.class)
    private Integer id; //ID
    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String buildingNumber; //楼号
    @NotNull
    private Integer houseNumber; //门牌号
    @NotEmpty
    @Pattern(regexp = "^\\S{2,20}$")
    private String name; //联系人姓名
    @Pattern(regexp = "^1[3-9]\\d{9}$")
    private String phone; //联系人电话
    private Integer userId; //用户ID
    @NotNull
    private Integer isDefault; //是否为默认地址 0 不是默认地址 1 默认地址
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间

    public interface Add extends Default {
    }

    public interface Update extends Default {
    }
}
