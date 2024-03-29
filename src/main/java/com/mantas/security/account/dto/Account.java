package com.mantas.security.account.dto;

import com.mantas.security.authority.dto.Authority;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Account implements Serializable {

    private static final long serialVersionUID = 4889732121514117646L;

    private Integer id;
    private Integer userId;
    private String name;
    private String token;
    private LocalDateTime expiration;
    private Boolean nonLocked;
    private Boolean status;
    private List<Authority> authorities;
}
