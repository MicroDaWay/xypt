package com.microdaway.xypt.mapper;

import com.microdaway.xypt.entity.Money;
import com.microdaway.xypt.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username}")
    User findByUsername(String username);

    @Insert("insert into user(username,password,create_time,update_time) " +
            "values(#{username},#{md5Password},now(),now())")
    void register(String username, String md5Password);

    @Update("update user set username=#{username},nickname=#{nickname},avatar=#{avatar}," +
            "gender=#{gender},phone=#{phone},update_time=now() where id=#{id}")
    void update(User user);

    @Select("select account_money from user where id = #{userId}")
    BigDecimal balance(Integer userId);

    @Update("update user set account_money=#{newMoney},update_time=now() where id=#{userId}")
    void payUpdate(Integer userId, BigDecimal newMoney);

    @Insert("insert into money(state,money_change,user_id,create_time,update_time) " +
            "values(1,#{money},#{userId},now(),now())")
    void payInsert(Integer userId, BigDecimal money);

    @Select("select * from money where user_id = #{userId} order by create_time desc")
    List<Money> incomeRecord(Integer userId);
}
