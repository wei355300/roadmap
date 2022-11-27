package com.mantas.drvet.mapper;

import org.apache.ibatis.annotations.*;

@Mapper
public interface DrvetMobileMapper {

    @Select("select count(1) from drvet_mobile where mobile=#{mobile}")
    int countByMobile(@Param("mobile") String mobile);

    @Select("select * from drvet_mobile where mobile=#{mobile}")
    String getMobile(@Param("mobile") String mobile);

    @Insert("insert into drvet_mobile (mobile) values (#{mobile})")
    void addMobile(@Param("mobile") String mobile);
}
