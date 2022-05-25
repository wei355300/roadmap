package com.mantas.tapd.config;

import com.mantas.tapd.dto.Project;
import com.mantas.tapd.dto.TapdAuth;
import lombok.Data;

import java.util.List;

@Data
public class TapdConfigProperties {

    private TapdAuth auth;
    private List<Project> projects;
}