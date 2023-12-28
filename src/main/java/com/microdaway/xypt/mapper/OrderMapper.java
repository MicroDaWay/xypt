package com.microdaway.xypt.mapper;

import com.microdaway.xypt.entity.Order;
import com.microdaway.xypt.entity.Rider;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface OrderMapper {
    @Insert("insert into `order`(trade_name,`desc`,fee,order_type,order_state,note,pay_user_id,pay_user_name,pay_user_address,pay_user_phone,create_time,update_time) " +
            "values(#{tradeName},#{desc},#{fee},#{orderType},1,#{note},#{payUserId},#{payUserName},#{payUserAddress},#{payUserPhone},now(),now())")
    void submit(Order order);

    @Select("select * from `order` where pay_user_id = #{userId} and order_state = #{orderState} order by create_time desc")
    List<Order> get(Integer orderState, Integer userId);

    @Select("select * from `order` where pay_user_id = #{userId} order by create_time desc")
    List<Order> getAll(Integer userId);

    @Select("select * from `order` where order_state = 1 and pay_user_id != #{userId}")
    List<Order> getPendingOrders(Integer userId);

    @Update("update `order` set order_state = #{orderState},update_time = now() where id = #{id}")
    void updateOrderState(Integer id, Integer orderState);

    @Update("update `order` set evaluate=#{evaluate},rate=#{rate},update_time=now() where id = #{id}")
    void evaluate(Order order);

    @Delete("delete from `order` where id = #{id}")
    void delete(Integer id);

    @Update("update `order` set receive_user_id=#{userId},receive_user_name=#{receiveUserName},receive_user_phone=#{receiveUserPhone} where id = #{id}")
    void riderReceive(Integer id, Integer userId, String receiveUserName, String receiveUserPhone);

    @Select("select * from `order` where receive_user_id = #{userId} order by create_time desc")
    List<Order> riderOrder(Integer userId);

    @Insert("insert into money(state,money_change,user_id,create_time,update_time) " +
            "values(#{state},#{fee},#{userId},now(),now())")
    void insertMoney(Integer state, Integer fee, Integer userId);

    @Select("select fee from `order` where id = #{id}")
    Integer getFee(Integer id);

    @Update("update user set account_money = #{newMoney}, update_time = now() where id = (select pay_user_id from `order` where id = #{id})")
    void refund(BigDecimal newMoney, Integer id);

    @Update("update user set account_money = #{newMoney}, update_time = now() where id = #{riderId}")
    void updateRiderMoney(BigDecimal newMoney, Integer riderId);

    @Select("select receive_user_id from `order` where id = #{id}")
    Integer findRider(Integer id);

    @Insert("insert into money(state,money_change,user_id,create_time,update_time) " +
            "values(2,#{fee},#{userId},now(),now())")
    void insertRefundMoney(Integer fee, Integer userId);

    @Select("select * from `order` where id = #{id}")
    Order getOrderById(Integer id);

    @Select("select * from rider where user_id = #{userId}")
    Rider getRider(Integer userId);

    @Select("select account_money from user where id = #{userId}")
    BigDecimal getAccountMoney(Integer userId);
}
