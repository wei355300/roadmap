package com.mantas.connector;

import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

@Slf4j
public class ParamInterceptor implements Interceptor {

    private List<ParamPair> params;

    public ParamInterceptor(List<ParamPair> params) {
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
