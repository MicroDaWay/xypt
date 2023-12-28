package com.microdaway.xypt.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Rider {
    private Integer id; //ID
    private String name; //姓名
    private String phone; //手机号
    private String idCard; //身份证号
    private String address; //常住地址
    private Integer userId; //用户ID
    private Integer examineState; //审核状态 0 待审核 1 审核通过 2 审核失败
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间
}
