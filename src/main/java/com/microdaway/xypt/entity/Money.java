package com.microdaway.xypt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Money {
    private Integer id; //ID
    private Integer state; //0 扣款 1充值
    private BigDecimal moneyChange; //变化的钱数
    private Integer userId; //用户ID
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime; //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime; //修改时间
}
