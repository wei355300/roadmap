package com.mantas.gitlab.service.impl;

import com.mantas.gitlab.GitlabUtils;
import com.mantas.gitlab.service.GitFileService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GitFileServiceImpl implements GitFileService {

    private GitlabUtils gitlabUtils;
    private String DEFAULT_BRANCH = "master";

    public GitFileServiceImpl(GitlabUtils gitlabUtils) {
        this.gitlabUtils = gitlabUtils;
    }

    /**
     * 获取文件内容,
     * 该文件是透传与gitlab, 内容采用base64加密,
     * <p>
     * 默认读取 master 分支的文件
     *
     * @param filePath
     */
    @Override
    public String getContent(String filePath) throws Exception {
        return gitlabUtils.getFileContent(filePath, DEFAULT_BRANCH);
    }

    @Override
    public String getContent(String filePath, String branch) throws Exception {
        return gitlabUtils.getFileContent(filePath, branch);
    }
}
