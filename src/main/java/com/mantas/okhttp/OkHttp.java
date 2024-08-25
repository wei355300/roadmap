package com.mantas.okhttp;

import com.fasterxml.jackson.core.JsonProcessingException;
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
        static Request build(String url, List<ParamPair> params) {
            Request.Builder reqBuilder = new Request.Builder();
            reqBuilder.url(url);
            FormBody.Builder formBuilder = new FormBody.Builder();
            params.forEach( p ->
                formBuilder.add(p.getName(), p.getValue())
            );
            RequestBody reqBody = formBuilder.build();
            reqBuilder.post(reqBody);
            return reqBuilder.build();
        }

        static <T> Request buildJson(String url, T params) throws JsonProcessingException {
            String jsonBody = JsonUtils.toJson(params);
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonBody);
            return new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
        }
    }

    private OkHttpClient client;

    public OkHttp(Set<Interceptor> interceptors) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        if (Objects.nonNull(interceptors)) {
            interceptors.forEach(clientBuilder::addInterceptor);
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

    public String post(String url, List<ParamPair> params) throws IOException {
        Request request = Post.build(url, params);
        return request(request);
    }

    public <T> T post(String url, List<ParamPair> params, Class<T> classOfT) throws IOException {
        Request request = Post.build(url, params);
        return request(request, classOfT);
    }

    public <T, R> R postJson(String url, T params, Class<R> classOfT) throws IOException {
        Request request = Post.buildJson(url, params);
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
