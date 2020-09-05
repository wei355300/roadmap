package com.mantas.tapd.service.impl;

import com.mantas.tapd.dto.Role;
import com.mantas.tapd.service.DeveloperService;
import com.mantas.tapd.connector.OkHttp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class DeveloperServiceImpl extends TapdBuiler implements DeveloperService {

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
