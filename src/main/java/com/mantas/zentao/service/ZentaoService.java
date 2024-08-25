package com.mantas.zentao.service;

import com.mantas.zentao.config.ZentaoConfigProperties;
import com.mantas.zentao.dto.Project;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class ZentaoService {

  private final ZentaoConfigProperties properties;

  public ZentaoService(ZentaoConfigProperties properties) {
    this.properties = properties;
  }

  public String fetchToken() throws IOException {
    return ZentaoApis.Tokens.get(
        properties.getHost(),
        properties.getAuth().getAccount(),
        properties.getAuth().getPassword());
  }

  public List<Project> getProjects() {
    return properties.getProjects();
  }
}
