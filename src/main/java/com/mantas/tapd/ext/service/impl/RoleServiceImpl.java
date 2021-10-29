package com.mantas.tapd.ext.service.impl;

import com.mantas.tapd.TapdResult;
import com.mantas.tapd.ext.conf.TapdURL;
import com.mantas.tapd.ext.dto.Role;
import com.mantas.tapd.ext.service.RoleService;
import com.mantas.tapd.ext.service.TapdRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    private TapdRequest tapdRequest;

    @Autowired
    public RoleServiceImpl(TapdRequest tapdRequest) {
        this.tapdRequest = tapdRequest;
    }

    @Override
    public List<Role> getRolesByProject(Integer projectId) {
        TapdResult<Map<String, String>> data = tapdRequest.get(TapdURL.URL.ROLES, tapdRequest.setParam(TapdURL.PARAM.WORKSPACE_ID, projectId.toString()), TapdResult.class);
        return convert(data);
    }

    private List<Role> convert(TapdResult<Map<String, String>> data) {
        return data.getData().entrySet().stream().map(m-> new Role(m.getKey(), m.getValue())).collect(Collectors.toList());
    }
}
