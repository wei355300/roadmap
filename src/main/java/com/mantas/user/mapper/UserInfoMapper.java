package com.mantas.user.mapper;

import com.mantas.user.dto.UserInfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserInfoMapper {

    @Select("select * from user_info where mobile=#{mobile}")
    UserInfo getUserByMobile(@Param("mobile") String mobile);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into user_info (mobile, name, nick, avatar_url) values (#{mobile}, #{name}, #{nick}, #{avatarUrl})")
    Integer addUser(UserInfo user);

    @Update("update user_info set name=#{name}, nick=#{nick}, avatar_url=#{avatarUrl} where mobile=#{mobile}")
    Integer updateUser(UserInfo user);
}
