package com.mantas.tapd.connector;

import com.google.gson.Gson;
import com.mantas.tapd.conf.TapdConf;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class OkHttp {

    @Autowired
    private TapdConf tapdConf;

    public <T> T get(String url, Map<String, String> params, Class<T> classOfT) throws IOException {

        OkHttpClient client = buildClient();
        Request request = buildRequest(buildUrl(url, params));
        String resBody = client.newCall(request).execute().body().string();
        return new Gson().fromJson(resBody, classOfT);
    }

//    public <T> List<T> list(String url, Map<String, String> params, Class<T> classOfT) throws IOException {
//
//        OkHttpClient client = buildClient();
//        Request request = buildRequest(buildUrl(url, params));
//        String resBody = client.newCall(request).execute().body().string();
//        Tapd<List<T>> t = new Gson().fromJson(resBody, new TypeToken<Tapd<List<Story>>>() {}.getType());
//        return t.getData();
//    }

    public <R> R post(String url) throws IOException {

        OkHttpClient client = buildClient();
        Request request = new Request.Builder().url(url).post(null).build();
        List o = new Gson().fromJson(client.newCall(request).execute().body().string(), List.class);
        return null;
    }

    private OkHttpClient buildClient() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.addInterceptor(new BasicInterceptor(tapdConf.getBasicAuthId(), tapdConf.getBasicAuthPwd()));
        return clientBuilder.build();
    }

    private HttpUrl buildUrl(String url, Map<String, String> params) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        if(!CollectionUtils.isEmpty(params)) {
            params.forEach((k, v) -> urlBuilder.addQueryParameter(k, v));
            if(Objects.isNull(params.get(TapdConf.TAPD.TAPD_PARAM_WORKSPACE_ID))) {
                urlBuilder.addQueryParameter(TapdConf.TAPD.TAPD_PARAM_WORKSPACE_ID, tapdConf.getDefaultWorkspaceId());
            }
        }
        return urlBuilder.build();
    }

    private Request buildRequest(HttpUrl url) {
        Request.Builder reqBuilder = new Request.Builder();
        reqBuilder.url(url);
        reqBuilder.get();
        return reqBuilder.build();
    }
}
