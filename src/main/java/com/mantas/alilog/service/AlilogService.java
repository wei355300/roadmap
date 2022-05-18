package com.mantas.alilog.service;

import com.mantas.alilog.dto.LogEntity;
import com.mantas.alilog.dto.LogResLine;

import java.util.Collection;
import java.util.List;

public interface AlilogService {

    Collection<LogEntity> getLogEntities();

    List<LogResLine> query(String entity, String logStore, String query, int fromTime, int toTime) throws Exception;
}
