package com.mantas.okhttp;

import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Slf4j
public class ParamInterceptor implements Interceptor {

    private List<ParamPair> params;

    public ParamInterceptor(List<ParamPair> params) {
        if (Objects.isNull(params) || params.isEmpty()) {
            throw new IllegalArgumentException("参数拦截器未指定参数!");
        }
        this.params = params;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        HttpUrl.Builder httpUrlBuilder = chain.request().url().newBuilder();
        params.forEach(p -> httpUrlBuilder.addQueryParameter(p.getName(), p.getValue()).build());
        HttpUrl httpUrl = httpUrlBuilder.build();
        Request request = chain.request().newBuilder().url(httpUrl).build();
        return chain.proceed(request);
    }
}
