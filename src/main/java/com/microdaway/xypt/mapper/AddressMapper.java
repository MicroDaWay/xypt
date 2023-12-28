package com.microdaway.xypt.mapper;

import com.microdaway.xypt.entity.Address;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AddressMapper {
    @Select("select * from address where user_id = #{userId} order by is_default desc,update_time desc")
    List<Address> address(Integer userId);

    @Update("update address set is_default = 0")
    void resetDefault();

    @Insert("insert into address(building_number,house_number,name,phone,user_id,is_default,create_time,update_time) " +
            "values(#{buildingNumber},#{houseNumber},#{name},#{phone},#{userId},#{isDefault},now(),now())")
    void addAddress(Address address);

    @Select("select * from address where id = #{id}")
    Address getAddressById(Integer id);


    @Update("update address set building_number=#{buildingNumber},house_number=#{houseNumber}," +
            "name=#{name},phone=#{phone},is_default=#{isDefault},update_time=now() where id=#{id}")
    void updateAddress(Address address);

    @Delete("delete from address where id = #{id}")
    void deleteAddress(Integer id);
}
