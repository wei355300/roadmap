package com.mantas.user.mapper;

import com.mantas.user.dto.UserInfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserInfoMapper {

    @Select("select * from user_info where mobile=#{mobile}")
    UserInfo getUserByMobile(@Param("mobile") String mobile);

    @Insert("insert into user_info (mobile, name, nick, avatar_url) values (#{mobile}, #{name}, #{nick}, #{avatarUrl}) on duplicate key update name=#{name}, nick=#{nick}, avatar_url=#{avatarUrl}")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Integer addOrUpdateUser(UserInfo user);
}
