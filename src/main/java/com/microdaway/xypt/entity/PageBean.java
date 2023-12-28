package com.microdaway.xypt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//分页返回结果对象
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<T> {
    private Integer pageNum; //当前页码
    private Integer pageSize; //每页显示的条数
    private Long total; //总条数
    private List<T> list; //当前页数据集合
}
