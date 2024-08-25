package com.mantas.zentao.config;

import com.mantas.zentao.dto.Auth;
import com.mantas.zentao.dto.Project;
import lombok.Data;

import java.util.List;

@Data
public class ZentaoConfigProperties {

    private String host;
    private Auth auth;
    private List<Project> projects;
}