package com.mantas.alilog.service.impl;

import com.aliyun.openservices.log.Client;
import com.aliyun.openservices.log.common.LogContent;
import com.aliyun.openservices.log.common.QueriedLog;
import com.aliyun.openservices.log.exception.LogException;
import com.aliyun.openservices.log.response.GetLogsResponse;
import com.mantas.alilog.dto.LogEntity;
import com.mantas.alilog.dto.LogQuery;
import com.mantas.alilog.dto.LogResLine;
import com.mantas.alilog.dto.LogStore;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Getter
public class AlilogClients {

    public class AlilogClient {
        private Client client;
        private LogEntity logEntity;

        public AlilogClient(LogEntity logEntity) {
            this.logEntity = logEntity;
            client = new Client(logEntity.getHost(), logEntity.getAccessId(), logEntity.getAccessKey());
        }

        public List<LogResLine> query(String logStoreName, String logQueryName, int fromTime, int toTime) throws LogException {
            LogStore logStore = getLogStore(logStoreName);
            LogQuery logQuery = getLogQuery(logStore, logQueryName);

            GetLogsResponse getLogsResponse = client.GetLogs(
                    logStore.getProject(),
                    logStore.getLogStore(),
                    fromTime,
                    toTime,
                    logQuery.getTopic(),
                    logQuery.getStatement());

            return toLogResLines(getLogsResponse.getLogs());
        }

        private LogStore getLogStore(String logStoreName) {
            return logEntity.getLogStores().stream().filter(s -> logStoreName.equals(s.getName())).findFirst().orElse(null);
        }

        private LogQuery getLogQuery(LogStore logStore, String queryName) {
            return logStore.getLogQueries().stream().filter(q -> queryName.equals(q.getName())).findFirst().orElse(null);
        }

        private List<LogResLine> toLogResLines(List<QueriedLog> responseLines) {
            List<LogResLine> ret = new ArrayList<>(responseLines.size());
            for (QueriedLog log : responseLines) {
                LogResLine line = new LogResLine();
                List<LogContent> contents = log.mLogItem.mContents;
                List<LogResLine.Column> columns = new ArrayList<>(contents.size());
                for (LogContent mContent : contents) {
                    LogResLine.Column column = new LogResLine.Column();
                    column.setLabel(mContent.mKey);
                    column.setValue(mContent.mValue);
                    columns.add(column);
                }
                line.setColumns(columns);
                ret.add(line);
            }
            return ret;
        }
    }

    private final static Map<String, AlilogClient> clients = new HashMap<>();
    private final List<LogEntity> entities;

    public AlilogClients(List<LogEntity> logEntities) {
        entities = logEntities;
        logEntities.forEach(prop -> {
            AlilogClient client = new AlilogClient(prop);
            clients.put(prop.getEntity(), client);
        });
    }

    public AlilogClient getClient(String entity) {
        return clients.get(entity);
    }

    public List<LogEntity> getEntities() {
        return entities;
    }

    public List<LogResLine> query(String entity, String logStore, String query, int fromTime, int toTime) throws LogException {
        return getClient(entity).query(logStore, query, fromTime, toTime);
    }
}
