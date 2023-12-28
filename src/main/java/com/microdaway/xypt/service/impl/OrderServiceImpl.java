package com.microdaway.xypt.service.impl;

import com.microdaway.xypt.entity.Order;
import com.microdaway.xypt.entity.Rider;
import com.microdaway.xypt.mapper.OrderMapper;
import com.microdaway.xypt.mapper.UserMapper;
import com.microdaway.xypt.service.OrderService;
import com.microdaway.xypt.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void submit(Order order) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        order.setPayUserId(userId);
        orderMapper.submit(order);
    }

    @Override
    public List<Order> get(Integer orderState) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Order> orderList = orderMapper.get(orderState, userId);
        return orderList;
    }

    @Override
    public List<Order> getAll() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Order> orderList = orderMapper.getAll(userId);
        return orderList;
    }

    @Override
    public List<Order> getPendingOrders() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Order> orderList = orderMapper.getPendingOrders(userId);
        return orderList;
    }

    @Override
    public void updateOrderState(Integer id, Integer orderState) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");

        //已收货 增加骑手的余额 并向money表中插入一条记录
        if (orderState.equals(4)) {
            Integer fee = orderMapper.getFee(id);
            Integer riderId = orderMapper.findRider(id);
            BigDecimal money = userMapper.balance(riderId);
            BigDecimal newMoney = money.add(new BigDecimal(fee));
            orderMapper.insertMoney(1, fee, riderId);
            orderMapper.updateRiderMoney(newMoney, riderId);
        }

        //取消订单 进行退款
        if (orderState.equals(5)) {
            Integer fee = orderMapper.getFee(id);
            BigDecimal money = userMapper.balance(userId);
            BigDecimal newMoney = money.add(new BigDecimal(fee));
            orderMapper.refund(newMoney, id);
            orderMapper.insertRefundMoney(fee, userId);
        }

        orderMapper.updateOrderState(id, orderState);
    }

    @Override
    public void evaluate(Order order) {
        orderMapper.evaluate(order);
    }

    @Override
    public void delete(Integer id) {
        orderMapper.delete(id);
    }

    @Override
    public void riderReceive(Integer id) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        Rider rider = orderMapper.getRider(userId);
        orderMapper.riderReceive(id, userId, rider.getName(), rider.getPhone());
    }

    @Override
    public List<Order> riderOrder() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Order> orderList = orderMapper.riderOrder(userId);
        return orderList;
    }

    @Override
    public void updateMoney(Integer fee) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        BigDecimal money = userMapper.balance(userId);
        BigDecimal newMoney = money.subtract(new BigDecimal(fee));
        orderMapper.insertMoney(0, fee, userId);
        userMapper.payUpdate(userId, newMoney);
    }

    @Override
    public Order getOrderById(Integer id) {
        Order order = orderMapper.getOrderById(id);
        return order;
    }

    @Override
    public BigDecimal getAccountMoney() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        BigDecimal accountMoney = orderMapper.getAccountMoney(userId);
        return accountMoney;
    }
}
