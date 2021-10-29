package com.mantas.tapd.ext.service.impl;

import com.mantas.tapd.ext.dto.Role;
import com.mantas.tapd.ext.service.DeveloperService;
import com.mantas.okhttp.OkHttp;
import com.mantas.tapd.ext.service.TapdRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class DeveloperServiceImpl extends TapdRequest implements DeveloperService {

    public DeveloperServiceImpl(@Autowired OkHttp okHttp) {
        super(okHttp);
    }

    @Override
    public List<Role> listRoles() {
//        try {
//            return list(TapdConf.TAPD.TAPD_URL_ROLE, null, Role.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return Collections.emptyList();
    }
}
