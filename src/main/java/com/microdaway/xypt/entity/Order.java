package com.microdaway.xypt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Order {
    private Integer id; //ID
    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String tradeName; //物品名称
    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String desc; //描述信息
    private Integer fee; //小费
    private Integer orderType; //订单类型 0 代拿快递 1 代取餐品 2 代买零食
    private Integer orderState; //订单状态 0 全部订单 1 待接单 2 待送达 3 待收货 4 待评价 5 已取消 6 已完成
    private String note; //备注
    private String evaluate; //评价
    private Integer rate; //评分
    private Integer payUserId; //下单人ID
    private String payUserName; //下单人姓名
    private String payUserAddress; //下单人地址
    private String payUserPhone; //下单人电话
    private Integer receiveUserId; //接单人ID
    private String receiveUserName; //接单人姓名
    private String receiveUserPhone; //接单人电话
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime; //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime; //修改时间
}
