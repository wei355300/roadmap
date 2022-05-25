package com.mantas.tapd.dto;

import lombok.Data;

@Data
public class TapdAuth {
    private String basicAuthId;
    private String basicAuthPwd;
}
