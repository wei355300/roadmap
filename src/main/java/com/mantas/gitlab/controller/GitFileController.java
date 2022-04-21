package com.mantas.gitlab.controller;

import com.mantas.controller.R;
import com.mantas.gitlab.service.GitFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@Slf4j
@RestController
@RequestMapping("/api/git/file")
public class GitFileController {

    private GitFileService gitFileService;

    public GitFileController(@Autowired GitFileService domainService) {
        this.gitFileService = domainService;
    }

    /**
     *
     * @param file 存放在Git上的文件, 为避免文件后缀等影响, 需要将文件名参数在http传输时先base64编码
     * @return
     * @throws Exception
     */
    @GetMapping("/{file}")
    public R<String> getGitFile(@PathVariable("file") String file) throws Exception {
        byte[] c = Base64.getDecoder().decode(file);
        String fileName = new String(c);
        log.info("获取Git文件: {}", fileName);
        return R.success(gitFileService.getContent(fileName));
    }
}
