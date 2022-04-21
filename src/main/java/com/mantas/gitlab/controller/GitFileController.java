package com.mantas.gitlab.controller;

import com.mantas.controller.R;
import com.mantas.gitlab.service.GitFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/git/file")
public class GitFileController {

    private GitFileService gitFileService;

    public GitFileController(@Autowired GitFileService domainService) {
        this.gitFileService = domainService;
    }

    @GetMapping("/{file}")
    public R<String> getGitFile(@PathVariable("file") String file) throws Exception {
        return R.success(gitFileService.getContent(file));
    }

//    @GetMapping("/{key}/detail")
//    public R<String> getDomainDetail(@PathVariable("key") String domainKey) throws Exception {
//        return R.success(gitFileService.getContent("domains.json", "master"));
//    }
}
