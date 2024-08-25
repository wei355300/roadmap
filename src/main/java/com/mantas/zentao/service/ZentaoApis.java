package com.mantas.zentao.service;

import com.mantas.okhttp.OkHttp;
import java.io.IOException;
import lombok.Builder;
import lombok.Data;

public class ZentaoApis {


  public static class Tokens {
      private final static String api = "/api.php/v1/tokens";

      @Data
      @Builder
      public static class Params {
          private String account;
          private String password;
      }

      @Data
      public static class Result {
          private String token;
      }

      public static String get(String host, String account, String password) throws IOException {
          Params params = Params.builder().account(account).password(password).build();
          Result result = new OkHttp(null).postJson(host.concat(api), params, Result.class);
          return result.getToken();
      }
  }
}
