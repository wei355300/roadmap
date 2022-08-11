package com.mantas.security.dingtalk;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mantas.security.account.dto.Account;
import com.mantas.security.token.TokenAuthenticationToken;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.Assert;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

public class DingtalkAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private ObjectMapper objectMapper;

    public DingtalkAuthenticationSuccessHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //todo 返回200状态码, 并携带账号信息
        Assert.isInstanceOf(TokenAuthenticationToken.class, authentication, () -> "Only TokenAuthenticationToken is supported");
        TokenAuthenticationToken tokenAuthenticationToken = (TokenAuthenticationToken) authentication;
        Account account = tokenAuthenticationToken.getAccount();

        response.setContentType(MediaType.APPLICATION_JSON);
        response.setStatus(HttpStatus.OK.value());

        objectMapper.writeValue(response.getWriter(), account);

//        OutputStream responseStream = response.getOutputStream();
//        String responseContent = JsonUtils.toJson(account);
//        response.getWriter().append(responseContent);
    }
}
