package com.mantas.connector;

import com.google.gson.Gson;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
        String resBody = client.newCall(request).execute().body().string();
        return new Gson().fromJson(resBody, classOfT);
    }

    public <R> R post(String url) throws IOException {
        //fixme buildPostReq()
        Request request = new Request.Builder().url(url).post(null).build();
        List o = new Gson().fromJson(client.newCall(request).execute().body().string(), List.class);
        return null;
    }

    private HttpUrl buildUrl(String url, List<ParamPair> params) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        if(!CollectionUtils.isEmpty(params)) {
            params.forEach((p) -> urlBuilder.addQueryParameter(p.getName(), p.getValue()));
        }
        return urlBuilder.build();
    }

    private Request buildPostReq(HttpUrl url, String body) {
        Request.Builder reqBuilder = new Request.Builder();
        reqBuilder.url(url);
//        reqBuilder.post(body);
        //fixme
        return reqBuilder.build();
    }

    private Request buildGetReq(HttpUrl url) {
        Request.Builder reqBuilder = new Request.Builder();
        reqBuilder.url(url);
        reqBuilder.get();
        return reqBuilder.build();
    }
}
