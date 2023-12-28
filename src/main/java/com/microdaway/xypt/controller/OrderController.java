package com.microdaway.xypt.controller;

import com.microdaway.xypt.entity.Order;
import com.microdaway.xypt.entity.Result;
import com.microdaway.xypt.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    //提交订单
    @PostMapping("/submit")
    public Result submit(@RequestBody @Validated Order order) {
        BigDecimal accountMoney = orderService.getAccountMoney();
        if (accountMoney.compareTo(new BigDecimal(order.getFee())) < 0) {
            return Result.error("账户余额不足");
        }
        orderService.submit(order);
        orderService.updateMoney(order.getFee());
        return Result.success();
    }

    //获取订单
    @GetMapping("/get")
    public Result<List<Order>> get(Integer orderState) {
        List<Order> orderList;
        if (orderState.equals(0)) {
            orderList = orderService.getAll();
        } else {
            orderList = orderService.get(orderState);
        }
        return Result.success(orderList);
    }

    //获取待接单的订单
    @GetMapping("/getPendingOrders")
    public Result<List<Order>> getPendingOrders() {
        List<Order> orderList = orderService.getPendingOrders();
        return Result.success(orderList);
    }

    //更新订单状态
    @PutMapping("/updateOrderState")
    public Result updateOrderState(Integer id, Integer orderState) {
        orderService.updateOrderState(id, orderState);
        return Result.success();
    }

    //订单评价
    @PostMapping("/evaluate")
    public Result evaluate(@RequestBody Order order) {
        orderService.evaluate(order);
        return Result.success();
    }

    //删除订单
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        orderService.delete(id);
        return Result.success();
    }

    //骑手接单
    @PutMapping("/riderReceive/{id}")
    public Result riderReceive(@PathVariable Integer id) {
        orderService.riderReceive(id);
        orderService.updateOrderState(id, 2);
        return Result.success();
    }

    //获取骑手订单
    @GetMapping("/riderOrder")
    public Result<List<Order>> riderOrder() {
        List<Order> orderList = orderService.riderOrder();
        return Result.success(orderList);
    }

    //根据id获取订单详情
    @GetMapping("/get/{id}")
    public Result<Order> getOrderById(@PathVariable Integer id) {
        Order order = orderService.getOrderById(id);
        return Result.success(order);
    }
}
