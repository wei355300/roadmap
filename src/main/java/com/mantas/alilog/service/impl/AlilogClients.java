package com.mantas.alilog.service.impl;

import com.aliyun.openservices.log.Client;
import com.aliyun.openservices.log.common.LogContent;
import com.aliyun.openservices.log.common.QueriedLog;
import com.aliyun.openservices.log.exception.LogException;
import com.aliyun.openservices.log.response.GetLogsResponse;
import com.mantas.alilog.dto.LogEntity;
import com.mantas.alilog.dto.LogQuery;
import com.mantas.alilog.dto.LogStore;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

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

        public void query(String logStoreName, String logQueryName, int fromTime, int toTime) throws LogException {
            // query = "*| select * from " + logstoreName;
//        System.out.println(String.format("ready to query logs from %s", getName()));
            //fromTime和toTime表示查询日志的时间范围，Unix时间戳格式。
//        int fromTime = (int) (System.currentTimeMillis()/1000 - 3600);
//        int toTime = fromTime + 3600;

            LogStore logStore = getLogStore(logStoreName);
            LogQuery logQuery = getLogQuery(logStore, logQueryName);


            GetLogsResponse getLogsResponse = client.GetLogs(
                    logStore.getProject(),
                    logStore.getLogStore(),
                    fromTime,
                    toTime,
                    logQuery.getTopic(),
                    logQuery.getFilter());
            for (QueriedLog log : getLogsResponse.getLogs()) {
                for (LogContent mContent : log.mLogItem.mContents) {
                    System.out.println(mContent.mKey + " : " + mContent.mValue);
                }
                System.out.println("********************");
            }
        }

        private LogStore getLogStore(String logStoreName) {
            return logEntity.getLogStores().stream().filter(s -> logStoreName.equals(s.getName())).findFirst().orElse(null);
        }

        private LogQuery getLogQuery(LogStore logStore, String queryName) {
            return logStore.getLogQueries().stream().filter(q -> queryName.equals(q.getName())).findFirst().orElse(null);
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

    public void query(String entity, String logStore, String query, int fromTime, int toTime) throws LogException {
        getClient(entity).query(logStore, query, fromTime, toTime);
    }
}
