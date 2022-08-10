package com.mantas.security.dingtalk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationEntryPointFailureHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class DingtalkAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public static final String DINGTALK_AUTHENTICATION_CALLBACK_URI = "/api/auth/dingtalk/callback";
    public static final String DINGTALK_AUTHENTICATION_CODE = "authCode";
    private String authCodeParameter = DINGTALK_AUTHENTICATION_CODE;

    /**
     * 失败后的操作,
     * 鉴权失败, 默认返回401错误码
     * 参考{@link #afterPropertiesSet()}
     */
    private AuthenticationFailureHandler authenticationFailureHandler = new AuthenticationEntryPointFailureHandler(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));

    public DingtalkAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(DINGTALK_AUTHENTICATION_CALLBACK_URI, authenticationManager);
        setAuthenticationFailureHandler(authenticationFailureHandler);
    }

    public DingtalkAuthenticationFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
        super(defaultFilterProcessesUrl, authenticationManager);
        setAuthenticationFailureHandler(authenticationFailureHandler);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String authCode = obtainAuthCode(request);
        authCode = (authCode != null) ? authCode.trim() : "";
        DingtalkAuthenticationToken authRequest = DingtalkAuthenticationToken.unauthenticated(authCode);
        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private String obtainAuthCode(HttpServletRequest request) {
        return request.getParameter(this.authCodeParameter);
    }

    protected void setDetails(HttpServletRequest request, DingtalkAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }
}
