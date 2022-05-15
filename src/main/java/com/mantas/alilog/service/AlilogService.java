package com.mantas.alilog.service;

import com.aliyun.openservices.log.exception.LogException;
import com.mantas.alilog.dto.LogQuery;

import java.util.Collection;

public interface AlilogService {

    void query(String entity, LogQuery query) throws LogException;

    Collection<String> getLogEntities();

    String getQueryOfEntity(String path) throws Exception;
}
