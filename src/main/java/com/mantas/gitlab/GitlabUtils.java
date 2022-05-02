package com.mantas.gitlab;

import com.mantas.gitlab.config.GitlabConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;

@Slf4j
public class GitlabUtils {

    private GitLabApi gitLabApi;
    private GitlabConfigProperties properties;

    public GitlabUtils(GitlabConfigProperties properties) {
        this.properties = properties;
        this.gitLabApi = new GitLabApi(properties.getUrl(), properties.getAccessToken());
    }

    public String getFileContent(String path, String branchName) throws GitLabApiException {
        log.info("get file by path: {}, branch: {}", path, branchName);
        return gitLabApi.getRepositoryFileApi().getFile(properties.getProjectId(), path, branchName).getContent();
    }
}
