package com.mantas.tapd.ext.dto.tapd;

import lombok.Data;

@Data
public class TapdAuth {
    private String basicAuthId;
    private String basicAuthPwd;
}
