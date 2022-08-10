package com.mantas.user.dto;

import lombok.Data;

@Data
public class UserInfo {

    private Integer id;
    private String avatarUrl;
    private String mobile;
    private String name;
    private String nick;
}
