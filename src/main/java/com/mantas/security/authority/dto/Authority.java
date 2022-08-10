package com.mantas.security.authority.dto;

import lombok.Data;

@Data
public class Authority {

    /**
     * 权限值
     *
     * 形式由具体的业务决定,
     * 可以是
     * - http 的 url,
     * - 权限编码
     * - 方法名称
     * 等等....
     */
    private String authority;
    private String action;
    private String scope;

    public Authority() {
    }

    public Authority(String authority, String action) {
        this.authority = authority;
        this.action = action;
    }

    public Authority(String authority, String action, String scope) {
        this.authority = authority;
        this.action = action;
        this.scope = scope;
    }

}
