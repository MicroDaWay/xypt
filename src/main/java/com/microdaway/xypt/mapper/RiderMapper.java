package com.microdaway.xypt.mapper;

import com.microdaway.xypt.entity.Rider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface RiderMapper {
    @Insert("insert into rider(name, phone, id_card, address, user_id, examine_state, create_time, update_time) " +
            "values(#{name}, #{phone}, #{idCard}, #{address}, #{userId}, #{examineState}, now(), now())")
    void examine(Rider rider);

    @Select("select * from rider where user_id = #{userId}")
    Rider get(Integer userId);

    @Update("update rider set name=#{name},phone=#{phone},id_card=#{idCard},address=#{address},update_time=now()")
    void update(Rider rider);

    @Select("select * from rider where examine_state = 0")
    List<Rider> awaitExamine();

    @Update("update rider set examine_state = 1,update_time = now() where id = #{id}")
    void examinePass(Integer id);

    @Update("update user set role = 2 where id = #{userId}")
    void updateRole(Integer userId);
}
