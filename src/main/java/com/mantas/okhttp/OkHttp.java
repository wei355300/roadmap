package com.mantas.okhttp;

import com.mantas.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Slf4j
public class OkHttp {

    static class Url {
        static HttpUrl build(String url, List<ParamPair> params) {
            HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
            if (!CollectionUtils.isEmpty(params)) {
                params.forEach((p) -> urlBuilder.addQueryParameter(p.getName(), p.getValue()));
            }
            return urlBuilder.build();
        }
    }

    static class Get {
        static Request build(HttpUrl url) {
            Request.Builder reqBuilder = new Request.Builder();
            reqBuilder.url(url);
            reqBuilder.get();
            return reqBuilder.build();
        }
    }

    static class Post {
        static Request build(String url, String body) {
            Request.Builder reqBuilder = new Request.Builder();
            reqBuilder.url(url);
            //fixme buildPostReq()
            RequestBody reqBody = null;
            reqBuilder.post(reqBody);
            return reqBuilder.build();
        }
    }

    private OkHttpClient client;

    public OkHttp(Set<Interceptor> interceptors) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        if (Objects.nonNull(interceptors)) {
            interceptors.forEach(interceptor -> clientBuilder.addInterceptor(interceptor));
        }
        client = clientBuilder.build();
    }

    public String get(String url, List<ParamPair> params) throws IOException {
        Request request = Get.build(Url.build(url, params));
        return request(request);
    }

    public <T> T get(String url, List<ParamPair> params, Class<T> classOfT) throws IOException {
        Request request = Get.build(Url.build(url, params));
        return request(request, classOfT);
    }

    public <T> T post(String url, String body, Class<T> classOfT) throws IOException {
        Request request = Post.build(url, body);
        return request(request, classOfT);
    }

    private <T> T request(Request request, Class<T> classOfT) throws IOException {
        String resBody = request(request);
        return JsonUtils.toObj(resBody, classOfT);
    }

    private String request(Request request) throws IOException {
        return client.newCall(request).execute().body().string();
    }
}
