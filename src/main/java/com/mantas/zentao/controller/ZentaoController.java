package com.mantas.zentao.controller;

import com.mantas.controller.R;
import com.mantas.zentao.dto.Project;
import com.mantas.zentao.service.ZentaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/zentao")
public class ZentaoController {

  private final ZentaoService zentaoService;

  public ZentaoController(ZentaoService zentaoService) {
    this.zentaoService = zentaoService;
  }

  @GetMapping("/token")
  public R<String> getToken() throws IOException {
    String token = zentaoService.fetchToken();
    log.info("zentao token result: {}", token);
    return R.success(token);
  }

  @GetMapping("/projects")
  public R<List<Project>> getProjects() throws IOException {
    List<Project> projects = zentaoService.getProjects();
    return R.success(projects);
  }
}
