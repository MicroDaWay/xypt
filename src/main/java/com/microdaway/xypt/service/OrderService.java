package com.microdaway.xypt.service;

import com.microdaway.xypt.entity.Order;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    void submit(Order order);

    List<Order> get(Integer orderState);

    List<Order> getAll();

    List<Order> getPendingOrders();

    void updateOrderState(Integer id, Integer orderState);

    void evaluate(Order order);

    void delete(Integer id);

    void riderReceive(Integer id);

    List<Order> riderOrder();

    void updateMoney(Integer fee);

    Order getOrderById(Integer id);

    BigDecimal getAccountMoney();
}
