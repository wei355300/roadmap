package com.mantas.tapd.ext.service;

import com.mantas.okhttp.ParamPair;
import com.mantas.okhttp.OkHttp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Slf4j
@Service
public class TapdRequest {

    private OkHttp okHttp;

    @Autowired
    public TapdRequest(OkHttp okHttp) {
        this.okHttp = okHttp;
    }

    public List<ParamPair> setParam(String key, String value) {
        List<ParamPair> params = new ArrayList<>();
        params.add(new ParamPair(key, value));
        return params;
    }

    public List<ParamPair> appendParams(List<ParamPair> pairs, String key, String value) {
        pairs.add(new ParamPair(key, value));
        return pairs;
    }

    public <T> T get(String url, List<ParamPair> params, Class<T> ret) {
       T result = null;

        try {
            result = okHttp.get(url, params, ret);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public <T> T post(String url, List<ParamPair> params, Class<T> ret) {

        T result = null;

        try {
            result = okHttp.get(url, params, ret);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

//    protected  <K extends TapdDataIt> List convert(TapdData<K> data, StructMapper mapper) {
//        if (Objects.nonNull(data) && Objects.nonNull(data.getData())) {
//            return data.getData().stream().map(m -> mapper.mapper(m.getEntity())).collect(Collectors.toList());
//        }
//        return Collections.emptyList();
//    }
}
