package com.mantas.alilog.service.impl;

import com.mantas.alilog.dto.LogEntity;
import com.mantas.alilog.dto.LogResLine;
import com.mantas.alilog.service.AlilogService;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.List;

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
    public List<LogResLine> query(String entity, String logStore, String query, int fromTime, int toTime) throws Exception {
        return alilogClients.query(entity, logStore, query, fromTime, toTime);
    }
}
