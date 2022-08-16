package com.mantas.security.ignore;

import com.mantas.security.token.TokenAuthenticationConverter;
import com.mantas.security.token.TokenAuthenticationToken;
import org.springframework.core.log.LogMessage;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationEntryPointFailureHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IgnoreAuthenticationFilter extends OncePerRequestFilter {

    private RequestMatcher requiresAuthenticationRequestMatcher;

    private AuthenticationManager authenticationManager;

    /**
     * 提取token,
     * 查看具体代码, 了解提取token至的细节
     */
    private TokenAuthenticationConverter authenticationConverter = new TokenAuthenticationConverter(new WebAuthenticationDetailsSource());

    /**
     * 失败后的操作,
     * 鉴权失败, 默认返回401错误码
     * 参考{@link #afterPropertiesSet()}
     */
    private AuthenticationFailureHandler authenticationFailureHandler;

    public IgnoreAuthenticationFilter(String filterProcessesUrl, AuthenticationManager authenticationManager) {
        Assert.notNull(authenticationManager, "authenticationManager cannot be null");
        Assert.notNull(filterProcessesUrl, "filterProcessesUrl cannot be null");
        this.authenticationManager = authenticationManager;
        this.requiresAuthenticationRequestMatcher = new AntPathRequestMatcher(filterProcessesUrl);
    }

    public IgnoreAuthenticationFilter(String filterProcessesUrl, AuthenticationManager authenticationManager, AuthenticationFailureHandler authenticationFailureHandler) {
        this(filterProcessesUrl, authenticationManager);
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Override
    public void afterPropertiesSet() {
        Assert.notNull(this.authenticationManager, "An AuthenticationManager is required");
        if (authenticationFailureHandler == null) {
            authenticationFailureHandler = new AuthenticationEntryPointFailureHandler(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            if (!requiresAuthentication(request, response)) {
                chain.doFilter(request, response);
                return;
            }
            TokenAuthenticationToken authRequest = this.authenticationConverter.convert(request);
            if (authRequest == null) {
                this.logger.trace("Did not process authentication request since failed to find token in request");
                authenticationFailureHandler.onAuthenticationFailure(request, response, new BadCredentialsException("token not found"));
                return;
            }
            String token = authRequest.getToken();
            this.logger.trace(LogMessage.format("Found token '%s' in Authorization request", token));
            Authentication authResult = this.authenticationManager.authenticate(authRequest);
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authResult);
            SecurityContextHolder.setContext(context);
            if (this.logger.isDebugEnabled()) {
                this.logger.debug(LogMessage.format("Set SecurityContextHolder to %s", authResult));
            }
        }
        catch (AuthenticationException ex) {
            SecurityContextHolder.clearContext();
            this.logger.debug("Failed to process authentication request", ex);
            authenticationFailureHandler.onAuthenticationFailure(request, response, ex);
            return;
        }

        chain.doFilter(request, response);
    }

    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        if (this.requiresAuthenticationRequestMatcher.matches(request)) {
            return true;
        }
        return false;
    }
}
