package com.mantas.tapd.ext.conf;

import com.alibaba.nacos.api.exception.NacosException;
import com.mantas.nacos.NacosConfigurator;
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
    public OkHttp okHttp(@Autowired NacosConfigurator nacosConfigurator, @Autowired NacosTapdxConf nacosTapdxConf) throws NacosException {
        TapdConf tapdConf = nacosConfigurator.getConfig(nacosTapdxConf, TapdConf.class);
        BasicInterceptor basicInterceptor = new BasicInterceptor(tapdConf.getAuth().getBasicAuthId(), tapdConf.getAuth().getBasicAuthPwd());
        Set<Interceptor> interceptors = new HashSet<>(1);
        interceptors.add(basicInterceptor);
        OkHttp okHttp = new OkHttp(interceptors);
        return okHttp;
    }

//    @Bean
//    public OkHttp okHttp(@Autowired TapdConf tapdConf) {
//        BasicInterceptor basicInterceptor = new BasicInterceptor(tapdConf.getBasicAuthId(), tapdConf.getBasicAuthPwd());
//        Set<Interceptor> interceptors = new HashSet<>(1);
//        interceptors.add(basicInterceptor);
//        OkHttp okHttp = new OkHttp(interceptors);
//        return okHttp;
//    }

}
