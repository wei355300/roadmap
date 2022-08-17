package com.mantas.user.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = -1385770057094900512L;
    private Integer id;
    private String avatarUrl;
    private String mobile;
    private String name;
    private String nick;
}
