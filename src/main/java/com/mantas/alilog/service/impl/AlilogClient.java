package com.mantas.alilog.service.impl;

import com.aliyun.openservices.log.Client;
import com.aliyun.openservices.log.common.LogContent;
import com.aliyun.openservices.log.common.QueriedLog;
import com.aliyun.openservices.log.exception.LogException;
import com.aliyun.openservices.log.response.GetLogsResponse;
import com.mantas.alilog.config.AlilogItemConfigProperties;
import com.mantas.alilog.dto.LogQuery;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class AlilogClient {

    private Client client;
    private AlilogItemConfigProperties properties;

    public AlilogClient(AlilogItemConfigProperties properties) {
        this.properties = properties;
        client = new Client(properties.getHost(), properties.getAccessId(), properties.getAccessKey());
    }

    public void query(LogQuery query) throws LogException {
        // query = "*| select * from " + logstoreName;
//        System.out.println(String.format("ready to query logs from %s", getName()));
        //fromTime和toTime表示查询日志的时间范围，Unix时间戳格式。
//        int fromTime = (int) (System.currentTimeMillis()/1000 - 3600);
//        int toTime = fromTime + 3600;
        GetLogsResponse getLogsResponse = client.GetLogs(
                query.getProjectName(),
                query.getLogstoreName(),
                query.getFromTime(),
                query.getToTIme(),
                query.getTopic(),
                query.getFilter());
        for (QueriedLog log : getLogsResponse.getLogs()) {
            for (LogContent mContent : log.mLogItem.mContents) {
                System.out.println(mContent.mKey + " : " + mContent.mValue);
            }
            System.out.println("********************");
        }
    }
}
