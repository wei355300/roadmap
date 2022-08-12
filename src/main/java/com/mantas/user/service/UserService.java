package com.mantas.user.service;

import com.mantas.user.dto.UserInfo;
import com.mantas.user.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserInfoMapper userInfoMapper;

    public UserService(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    public UserInfo getUserByMobile(String mobile) {
        return userInfoMapper.getUserByMobile(mobile);
    }

    /**
     * 新增用户, 并返回用户的最新数据
     * @param user
     * @return
     */
    public UserInfo addOrUpdateUser(UserInfo user) {
        Integer userId = userInfoMapper.addOrUpdateUser(user);
        user.setId(userId);
        return user;
    }
}
