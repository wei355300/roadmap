package com.mantas.tapd.ext.config;

import com.mantas.tapd.ext.dto.tapd.TapdAuth;
import com.mantas.tapd.ext.dto.tapd.TapdProject;
import lombok.Data;

import java.util.List;

@Data
public class TapdConf {

    private TapdAuth auth;
    private List<TapdProject> projects;
}