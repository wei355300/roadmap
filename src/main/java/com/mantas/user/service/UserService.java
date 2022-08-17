package com.mantas.user.service;

import com.mantas.user.dto.UserInfo;
import com.mantas.user.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private UserInfoMapper userInfoMapper;

    public UserService(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    public UserInfo getUserByMobile(String mobile) {
        return userInfoMapper.getUserByMobile(mobile);
    }

    @Transactional
    public UserInfo addUser(UserInfo user) {
        userInfoMapper.addUser(user);
        return user;
    }

    @Transactional
    public void updateUser(UserInfo user) {
        userInfoMapper.updateUser(user);
    }
}
