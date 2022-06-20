package com.mantas.alilog.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.mantas.alilog.dto.LogEntity;
import com.mantas.alilog.dto.LogQuery;
import com.mantas.alilog.dto.LogStore;
import com.mantas.gitlab.GitlabUtils;
import com.mantas.utils.JsonUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.gitlab4j.api.GitLabApiException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Data
@Slf4j
public class AlilogConfigPropertiesParser {

    @Data
    public static class GitFileContent {
        private String name;
        private String projectName;
        private String logStoreName;
        private List<GitFileQuery> queries;
    }

    @Data
    public static class GitFileQuery {
        private String name;
        private String topic;
        private String filter;
    }

    @Mapper
    public interface LogEntityMapper {
        LogEntityMapper mapper = Mappers.getMapper(LogEntityMapper.class);

        @Mapping(source = "entity", target = "entity")
        @Mapping(source = "desc", target = "desc")
        @Mapping(source = "accessId", target = "accessId")
        @Mapping(source = "accessKey", target = "accessKey")
        @Mapping(source = "host", target = "host")
        LogEntity logentity(AlilogConfigProperties.AlilogItemConfigProperties properties);
    }

    @Mapper
    public interface LogStoreMapper {
        LogStoreMapper mapper = Mappers.getMapper(LogStoreMapper.class);

        @Mapping(source = "name", target = "name")
        @Mapping(source = "projectName", target = "project")
        @Mapping(source = "logStoreName", target = "logStore")
        LogStore logStore(GitFileContent store);
    }

    @Mapper
    public interface LogQueryMapper {
        LogQueryMapper mapper = Mappers.getMapper(LogQueryMapper.class);

        @Mapping(source = "name", target = "name")
        @Mapping(source = "topic", target = "topic")
        @Mapping(source = "filter", target = "statement")
        LogQuery logQuery(GitFileQuery query);
    }

    public List<LogEntity> parse(AlilogConfigProperties properties) throws Exception {
        log.debug("parsed by: \n {}", properties);
        List<LogEntity> entities = new ArrayList<>(properties.getEntities().size());
        for (AlilogConfigProperties.AlilogItemConfigProperties e : properties.getEntities()) {
            LogEntity entity = parseEntity(e);
            entities.add(entity);
        }
        return entities;
    }

    private LogEntity parseEntity(AlilogConfigProperties.AlilogItemConfigProperties entityProperties) throws GitLabApiException, JsonProcessingException {
        LogEntity entity = LogEntityMapper.mapper.logentity(entityProperties);
        entity.setLogStores(parseLogStoreGit(entityProperties.getGit()));
        return entity;
    }

    private List<LogStore> parseLogStoreGit(AlilogConfigProperties.AlilogItemGitConfigProperties gitLogStoreProperties) throws GitLabApiException, JsonProcessingException {
        //通过gitlab获取到logstore的配置
        GitlabUtils gitlabUtils = new GitlabUtils((gitLogStoreProperties));
        String fileContent = gitlabUtils.getFileContent(gitLogStoreProperties.getFile(), "master");

        fileContent = new String(Base64.getDecoder().decode(fileContent));

        log.debug("git file content: \n {}", fileContent);

        TypeReference<ArrayList<GitFileContent>> listClazz = new TypeReference<ArrayList<GitFileContent>>() {
        };
        List<GitFileContent> gitContent = JsonUtils.toObj(fileContent, listClazz);

        List<LogStore> logStores = new ArrayList<>(gitContent.size());
        gitContent.forEach(g -> {
            LogStore logStore = LogStoreMapper.mapper.logStore(g);
            logStore.setLogQueries(parseLogQueryGit(g.getQueries()));
            logStores.add(logStore);
        });

        return logStores;
    }

    private List<LogQuery> parseLogQueryGit(List<GitFileQuery> queries) {
        List<LogQuery> logQueries = new ArrayList<>(queries.size());
        for (GitFileQuery gitQuery : queries) {
            logQueries.add(LogQueryMapper.mapper.logQuery(gitQuery));
        }
        return logQueries;
    }
}
