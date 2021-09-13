package com.mantas.tapd.conf;

import com.mantas.connector.BasicInterceptor;
import com.mantas.connector.OkHttp;
import com.mantas.connector.ParamInterceptor;
import com.mantas.connector.ParamPair;
import okhttp3.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @ConditionalOnProperty(name = "tapd", matchIfMissing = true)
    @Bean
    public TapdConf tapdConf() throws Exception {
        return new TapdConf();
    }

    @Bean
    public OkHttp okHttp(@Autowired TapdConf tapdConf) {
        BasicInterceptor basicInterceptor = new BasicInterceptor(tapdConf.getBasicAuthId(), tapdConf.getBasicAuthPwd());

        List<ParamPair> paramPairs = new ArrayList<>();
        paramPairs.add(new ParamPair(TapdURL.TAPD_PARAM_WORKSPACE_ID, tapdConf.getDefaultWorkspaceId()));
        ParamInterceptor paramInterceptor = new ParamInterceptor(paramPairs);

        Set<Interceptor> interceptors = new HashSet<>(2);
        interceptors.add(basicInterceptor);
        interceptors.add(paramInterceptor);
        OkHttp okHttp = new OkHttp(interceptors);
        return okHttp;
    }

}
