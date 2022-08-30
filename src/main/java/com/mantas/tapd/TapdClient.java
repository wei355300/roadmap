package com.mantas.tapd;

import com.jayway.jsonpath.TypeRef;
import com.mantas.okhttp.OkHttp;
import com.mantas.okhttp.ParamPair;
import com.mantas.tapd.exception.TapdException;
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

    public List<ParamPair> buildParam(String key, String value) {
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

    public <T> T get(String url, List<ParamPair> params, TypeRef<T> ref, String pattern) throws IOException {
        String body = get(url, params);
        return JsonPathUtils.to(body, pattern, ref);
    }

    public Boolean post(String url, List<ParamPair> params) throws TapdException {
        try {
            String resBody = okHttp.post(url, params);
            if (!isSuccessful(resBody)) {
                throw new TapdException(errInfo(resBody));
            }
            return Boolean.TRUE;
        } catch (IOException e) {
            log.warn("发送post请求失败, url: " + url, e);
            throw new TapdException("Tapd服务处理失败");
        }
    }

    public <T> T post(String url, List<ParamPair> params, Class<T> ret) throws TapdException {
        T result = null;
        try {
            result = okHttp.get(url, params, ret);
        } catch (IOException e) {
            log.warn("发送post请求失败, url: " + url, e);
            throw new TapdException("Tapd服务处理失败");
        }
        return result;
    }

    private String errInfo(String body) {
        return JsonPathUtils.to(body, "$.info", String.class);
    }

    private Boolean isSuccessful(String body) {
        Integer status = JsonPathUtils.to(body, "$.status", Integer.class);
        return 1 == status.intValue();
    }
}
