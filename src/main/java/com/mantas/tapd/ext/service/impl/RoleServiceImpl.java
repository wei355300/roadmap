package com.mantas.tapd.ext.service.impl;

import com.mantas.okhttp.ParamPair;
import com.mantas.tapd.TapdResult;
import com.mantas.tapd.TapdUser;
import com.mantas.tapd.TapdUserResult;
import com.mantas.tapd.ext.conf.TapdURL;
import com.mantas.tapd.ext.dto.Role;
import com.mantas.tapd.ext.dto.Worker;
import com.mantas.tapd.ext.dto.mapper.RoleConvert;
import com.mantas.tapd.ext.service.RoleService;
import com.mantas.tapd.ext.service.TapdRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
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

    /**
     * 按 projectId 缓存数据
     * <p>
     * 缓存清除规则查看: {@link #cacheEvict}
     */
    @Cacheable(cacheNames = "tapd-roles", key = "'tapd-roles-'+#projectId")
    @Override
    public List<Role> getRolesByProject(Integer projectId) {
        TapdResult<Map<String, String>> data = tapdRequest.get(TapdURL.URL.ROLES, tapdRequest.setParam(TapdURL.PARAM.WORKSPACE_ID, projectId.toString()), TapdResult.class);
        return convertRole(data);
    }

    /**
     * 按 projectId 缓存数据
     * <p>
     * 缓存清除规则查看: {@link #cacheEvict}
     */
    @Cacheable(cacheNames = "tapd-users", key = "'tapd-users-'+#projectId")
    @Override
    public List<Worker> getWorkersByRole(Integer projectId) {
        List<ParamPair> pairs = tapdRequest.setParam(TapdURL.PARAM.WORKSPACE_ID, projectId.toString());
        tapdRequest.appendParams(pairs, TapdURL.PARAM.FIELDS, "user,role_id,name,email");

        TapdResult<List<TapdUser>> data = tapdRequest.get(TapdURL.URL.USERS, pairs, TapdUserResult.class);
        return convertUser(data);
    }

    /**
     * 每隔 10 分钟, 清楚缓存
     */
    @CacheEvict(allEntries = true, value = {"tapd-users", "tapd-roles"})
    @Scheduled(fixedDelay = 10 * 60 * 1000, initialDelay = 2000)
    public void cacheEvict() {
        log.info("evict tapd: users|roles cached");
    }

    private List<Role> convertRole(TapdResult<Map<String, String>> data) {
        return data.getData().entrySet().stream().map(m -> new Role(m.getKey(), m.getValue())).collect(Collectors.toList());
    }

    private List<Worker> convertUser(TapdResult<List<TapdUser>> data) {
        return data.getData().stream().map(u -> RoleConvert.INSTANCE.toWorker(u.getUserWorkspace())).collect(Collectors.toList());
    }
}
