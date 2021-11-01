package com.mantas.tapd.ext.dto;

import lombok.Data;

import java.util.List;

@Data
public class Worker {

     private String user;
     private String name;
     private String email;
     private List<String> roles;
}
