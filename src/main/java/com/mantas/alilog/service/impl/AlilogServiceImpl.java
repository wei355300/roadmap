package com.mantas.alilog.service.impl;

import com.aliyun.openservices.log.exception.LogException;
import com.mantas.alilog.config.AlilogConfigProperties.AlilogItemConfigProperties;
import com.mantas.alilog.dto.LogEntity;
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


    private AlilogClients alilogClients;

    public AlilogServiceImpl(AlilogClients alilogClients) {
        this.alilogClients = alilogClients;
    }

    @Override
    public Collection<LogEntity> getLogEntities() {
        return alilogClients.getEntities();
    }

    @Override
    public void query(String entity, String logStore, String query, int fromTime, int toTime) throws Exception {
        try {
            alilogClients.query(entity, logStore, query, fromTime, toTime);
        } catch (LogException e) {
            throw new Exception();
        }
    }
}
