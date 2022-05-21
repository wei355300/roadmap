package com.mantas.tapd.origin;

import com.mantas.okhttp.OkHttp;
import com.mantas.okhttp.ParamPair;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TapdClient {

    private OkHttp okHttp;

    public TapdClient(OkHttp okHttp) {
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

    public String get(String url, List<ParamPair> params) throws IOException {
        return okHttp.get(url, params);
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
}
