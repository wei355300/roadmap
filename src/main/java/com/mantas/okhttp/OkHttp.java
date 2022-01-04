package com.mantas.okhttp;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Slf4j
public class OkHttp {

    private OkHttpClient client;

    public OkHttp(Set<Interceptor> interceptors) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        if (Objects.nonNull(interceptors)) {
            interceptors.forEach( interceptor -> clientBuilder.addInterceptor(interceptor));
        }
        client = clientBuilder.build();
    }

    public <T> T get(String url, List<ParamPair> params, Class<T> classOfT) throws IOException {
        Request request = buildGetReq(buildUrl(url, params));
        return req(request, classOfT);
    }

    public <T> T post(String url, String body, Class<T> classOfT) throws IOException {
        Request request = buildPostReq(url, body);
        return req(request, classOfT);
    }

    private <T> T req(Request request, Class<T> classOfT) throws IOException {
        String resBody = client.newCall(request).execute().body().string();
        return new Gson().fromJson(resBody, classOfT);
    }

    private HttpUrl buildUrl(String url, List<ParamPair> params) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        if(!CollectionUtils.isEmpty(params)) {
            params.forEach((p) -> urlBuilder.addQueryParameter(p.getName(), p.getValue()));
        }
        return urlBuilder.build();
    }

    private Request buildPostReq(String url, String body) {
        Request.Builder reqBuilder = new Request.Builder();
        reqBuilder.url(url);
        //fixme buildPostReq()
        RequestBody reqBody = null;
        reqBuilder.post(reqBody);
        return reqBuilder.build();
    }

    private Request buildGetReq(HttpUrl url) {
        Request.Builder reqBuilder = new Request.Builder();
        reqBuilder.url(url);
        reqBuilder.get();
        return reqBuilder.build();
    }
}
