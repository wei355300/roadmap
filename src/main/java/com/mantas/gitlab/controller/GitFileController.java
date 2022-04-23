package com.mantas.gitlab.controller;

import com.mantas.controller.R;
import com.mantas.gitlab.service.GitFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;

@Slf4j
@RestController
@RequestMapping("/api/git/file")
public class GitFileController {

    private GitFileService gitFileService;

    public GitFileController(@Autowired GitFileService domainService) {
        this.gitFileService = domainService;
    }

    /**
     * @param file 存放在Git上的文件, 为避免文件后缀等影响, 需要将文件名参数在http传输时先base64编码
     * @return
     * @throws Exception
     */
    @GetMapping("/")
    public R<String> getGitFile(@RequestParam("file") String file) throws Exception {
        String fileName = URLDecoder.decode(file, "UTF-8");
        log.info("获取Git文件: {}", fileName);
        return R.success(gitFileService.getContent(fileName));
    }

    /**
     * @param file 存放在Git上的文件, 为避免文件后缀等影响, 需要将文件名参数在http传输时先base64编码
     * @return
     * @throws Exception
     */
    @GetMapping("/branch")
    public R<String> getGitFileWithBranch(@RequestParam("file") String file, @RequestParam("branch") String branch) throws Exception {
        String fileName = URLDecoder.decode(file, "UTF-8");
        log.info("获取Git文件: {}", fileName);
        return R.success(gitFileService.getContent(fileName, branch));
    }
}
