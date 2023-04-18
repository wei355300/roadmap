package com.mantas.tapd.controller.req;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class GetTraceProjectParam {
    @NotNull
    private Integer id;
    private String name;
    private List<GetTraceRoleParam> roles;
    private List<GetTraceIterationParam> iterations;
    private String startDate;
    private String endDate;
    private String[] status;
}
