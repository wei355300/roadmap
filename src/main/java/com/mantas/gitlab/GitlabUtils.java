package com.mantas.gitlab;

import com.mantas.gitlab.config.GitlabConfigProperties;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;

public class GitlabUtils {

    private GitLabApi gitLabApi;
    private GitlabConfigProperties properties;

    public GitlabUtils(GitlabConfigProperties properties) {
        this.properties = properties;
        this.gitLabApi = new GitLabApi(properties.getUrl(), properties.getAccessToken());
    }

    public String getFileContent(String path, String branchName) throws GitLabApiException {
        return gitLabApi.getRepositoryFileApi().getFile(properties.getProjectId(), path, branchName).getContent();
    }
}
