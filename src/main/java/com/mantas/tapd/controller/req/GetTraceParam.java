package com.mantas.tapd.controller.req;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class GetTraceParam {
    @NotEmpty
    private List<GetTraceProjectParam> query;
}
