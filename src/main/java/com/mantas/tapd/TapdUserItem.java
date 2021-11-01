package com.mantas.tapd;

import lombok.Data;

import java.util.List;

@Data
public class TapdUserItem {
    private String user;
    private String name;
    private String email;
    private List<String> role_id;
}
