package com.mantas.drvet.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DrvetMobileMapper {

    @Select("select count(1) from drvet_mobile where mobile=#{mobile}")
    int countByMobile(@Param("mobile") String mobile);
}
