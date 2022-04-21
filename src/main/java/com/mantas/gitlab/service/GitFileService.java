package com.mantas.gitlab.service;

public interface GitFileService {

    /**
     * 获取文件内容,
     * 该文件是透传与gitlab, 内容采用base64加密,
     * <p>
     * 默认读取 master 分支的文件
     */
    String getContent(String filePath) throws Exception;

    /**
     * 获取文件内容,
     * 该文件是透传与gitlab, 内容采用base64加密,
     */
    String getContent(String filePath, String branch) throws Exception;
}
