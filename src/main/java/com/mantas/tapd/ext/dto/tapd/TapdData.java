package com.mantas.tapd.ext.dto.tapd;

import lombok.Data;

import java.util.List;

@Data
public class TapdData<T extends TapdDataIt> {

    private List<T> data;
}
