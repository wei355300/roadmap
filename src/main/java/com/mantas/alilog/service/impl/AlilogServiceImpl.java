package com.mantas.alilog.service.impl;

import com.aliyun.openservices.log.exception.LogException;
import com.mantas.alilog.config.AlilogItemConfigProperties;
import com.mantas.alilog.dto.LogQuery;
import com.mantas.alilog.service.AlilogService;
import com.mantas.gitlab.service.GitFileService;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class AlilogServiceImpl implements AlilogService {


    private GitFileService gitFileService;
    private final static Map<String, AlilogClient> clients = new HashMap<>();

    public AlilogServiceImpl(List<AlilogItemConfigProperties> properties, GitFileService gitFileService) {
        properties.forEach(prop -> {
            AlilogClient client = new AlilogClient(prop);
            clients.put(prop.getEntity(), client);
        });
        this.gitFileService = gitFileService;
    }

    @Override
    public void query(String entity, LogQuery query) throws LogException {
        clients.get(entity).query(query);
    }

    @Override
    public Collection<String> getLogEntities() {
        return clients.keySet();
    }

    @Override
    public String getQueryOfEntity(String path) throws Exception {
        return gitFileService.getContent(path);
    }
}
