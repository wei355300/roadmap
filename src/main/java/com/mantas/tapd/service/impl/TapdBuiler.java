package com.mantas.tapd.service.impl;

import com.mantas.tapd.dto.mapper.StructMapper;
import com.mantas.tapd.dto.tapd.TapdData;
import com.mantas.tapd.dto.tapd.TapdDataIt;
import com.mantas.tapd.connector.OkHttp;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public abstract class TapdBuiler {

    private OkHttp okHttp;

    protected TapdBuiler(OkHttp okHttp) {
        this.okHttp = okHttp;
    }

    protected Map<String, String> setParam(String key, String value) {
        Map params = new HashMap();
        params.put(key, value);
        return params;
    }

    protected <T> T get(String url, Map<String, String> params, Class<T> ret) {
       T result = null;

        try {
            result = okHttp.get(url, params, ret);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    protected <T> T post(String url, Map<String, String> params, Class<T> ret) {

        T result = null;

        try {
            result = okHttp.get(url, params, ret);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    protected  <K extends TapdDataIt> List convert(TapdData<K> data, StructMapper mapper) {
        if (Objects.nonNull(data) && Objects.nonNull(data.getData())) {
            return data.getData().stream().map(m -> mapper.mapper(m.getEntity())).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
