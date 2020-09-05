package com.mantas.account.mock;

import lombok.Data;

@Data
public class CurrentAuthority {

    private String status = "ok";
    private String type = "account";
    private String currentAuthority = "admin";
}
