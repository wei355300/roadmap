package com.mantas.tapd.ext.conf;

import com.mantas.okhttp.BasicInterceptor;
import com.mantas.okhttp.OkHttp;
import okhttp3.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class TapdConfiguration {

    @Bean
    public OkHttp okHttp(@Autowired TapdConf tapdConf) {
        BasicInterceptor basicInterceptor = new BasicInterceptor(tapdConf.getBasicAuthId(), tapdConf.getBasicAuthPwd());
        Set<Interceptor> interceptors = new HashSet<>(1);
        interceptors.add(basicInterceptor);
        OkHttp okHttp = new OkHttp(interceptors);
        return okHttp;
    }

}
