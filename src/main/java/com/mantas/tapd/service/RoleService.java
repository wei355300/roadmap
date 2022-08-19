package com.mantas.tapd.service;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import com.mantas.okhttp.ParamPair;
import com.mantas.tapd.dto.Role;
import com.mantas.tapd.dto.Worker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class RoleService {

    private TapdClient tapdClient;

    public RoleService(TapdClient tapdClient) {
        this.tapdClient = tapdClient;
    }

    /**
     * 按 projectId 缓存数据
     * <p>
     * 缓存清除规则查看: {@link #cacheEvict}
     */
    @Cacheable(cacheNames = "tapd-roles", key = "'tapd-roles-'+#projectId")
    public List<Role> getRolesByProject(Integer projectId) {
        return requestRoles(projectId);
    }

    /**
     * 按 projectId 缓存数据
     * <p>
     * 缓存清除规则查看: {@link #cacheEvict}
     */
    @Cacheable(cacheNames = "tapd-users", key = "'tapd-users-'+#projectId")
    public List<Worker> getUsersByProject(Integer projectId) {
        log.info("getUsersByProject: {}", projectId);
        return requestUsers(projectId);
    }

    public List<Worker> getUsersByProject(Integer projectId, Collection<Role> roles) {
        List<Worker> allWorkers = getUsersByProject(projectId);
        return allWorkers.stream().filter(w -> {
            for (Role r : roles) {
                if (w.getRoles().contains(r.getId())) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());
    }

    /**
     * 每隔 10 分钟, 清楚缓存
     */
    @CacheEvict(allEntries = true, value = {"tapd-users", "tapd-roles"})
    @Scheduled(fixedDelay = 10 * 60 * 1000, initialDelay = 2000)
    public void cacheEvict() {
        log.info("evict tapd: users|roles cached");
    }


    private List<Worker> requestUsers(Integer projectId) {
        List<ParamPair> pairs = tapdClient.setParam(TapdURL.PARAM.WORKSPACE_ID, projectId.toString());
        tapdClient.appendParams(pairs, TapdURL.PARAM.FIELDS, "user,role_id,name,email");

        try {
            String body = tapdClient.get(TapdURL.URL.USERS, pairs);
            return body2User(body);
        } catch (IOException e) {
            log.warn("requestRoles err: \n {}", e);
        }
        return Collections.EMPTY_LIST;
    }

    private List<Role> requestRoles(Integer projectId) {
        List<ParamPair> pairs = tapdClient.setParam(TapdURL.PARAM.WORKSPACE_ID, projectId.toString());
        try {
            String body = tapdClient.get(TapdURL.URL.ROLES, pairs);
            return body2Role(body);
        } catch (IOException e) {
            log.warn("requestRoles err: \n {}", e);
        }
        return Collections.EMPTY_LIST;
    }

    private List<Role> body2Role(String body) {
        Map<String, String> map = JsonPath.read(body, "$.data");
        List<Role> roles = new ArrayList<>(map.size());
        map.forEach((k, v) -> roles.add(new Role(k, v)));
        return roles;
    }

    private List<Worker> body2User(String body) {
        ReadContext ctx = JsonPath.parse(body);
        List<Map<String, String>> list = ctx.read("$.data[*].UserWorkspace");
        List<Worker> users = new ArrayList<>(list.size());
        for (int i = 0; i < list.size(); i++) {
            Worker worker = new Worker();
            Map<String, String> m = list.get(i);

            worker.setUser(m.get("user"));
            worker.setName(m.get("name"));
            worker.setEmail(m.get("email"));

            List<String> roleIds = ctx.read("$.data[" + i + "].UserWorkspace.role_id");
            worker.setRoles(roleIds);

            users.add(worker);
        }
        return users;
    }
}
