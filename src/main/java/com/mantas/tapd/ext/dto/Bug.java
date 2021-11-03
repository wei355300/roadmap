package com.mantas.tapd.ext.dto;

import lombok.Data;

@Data
public class Bug {

    private String id;
    private String title;
    private String[] developer;
    private String[] tester;
    private String description;
    private String priority; //优先级
    private String reporter; //创建人
    private String severity; //严重程度
    private String status;
    private int projectId;
    private String iterationId;
}
