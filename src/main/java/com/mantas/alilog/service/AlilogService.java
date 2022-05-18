package com.mantas.alilog.service;

import com.mantas.alilog.dto.LogEntity;

import java.util.Collection;

public interface AlilogService {

    Collection<LogEntity> getLogEntities();

    void query(String entity, String logStore, String query, int fromTime, int toTime) throws Exception;
}
