package com.mantas.tapd.origin.dto;

import lombok.Data;

@Data
public class TapdBugItem {
    private String id;
    private String title;
    private String description;
    private String workspace_id;
    private String priority; //优先级
    private String reporter; //创建人
    private String severity; //严重程度
    private String status;
//    private String current_owner; //当前处理人
    private String de; //开发人员
    private String te; //测试人员
    private String iteration_id;
}