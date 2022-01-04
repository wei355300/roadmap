package com.mantas.tapd.ext.conf;

import com.alibaba.nacos.api.exception.NacosException;
import com.mantas.nacos.NacosConfigurator;
import com.mantas.okhttp.BasicInterceptor;
import com.mantas.okhttp.OkHttp;
import com.mantas.tapd.ext.service.TapdRequest;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Configuration
public class TapdConfiguration {

    @Bean
    public OkHttp okHttp(@Autowired NacosTapdxConf nacosTapdxConf) throws NacosException {
        TapdConf tapdConf = NacosConfigurator.getConfig(nacosTapdxConf, TapdConf.class);
        log.info("nacos server config content: {}, {}", tapdConf.getAuth().getBasicAuthId(), tapdConf);
        BasicInterceptor basicInterceptor = new BasicInterceptor(tapdConf.getAuth().getBasicAuthId(), tapdConf.getAuth().getBasicAuthPwd());
        Set<Interceptor> interceptors = new HashSet<>(1);
        interceptors.add(basicInterceptor);
        OkHttp okHttp = new OkHttp(interceptors);
        return okHttp;
    }

    @Bean
    public TapdRequest tapdRequest(@Autowired OkHttp okHttp) {
        return new TapdRequest(okHttp);
    }

}
