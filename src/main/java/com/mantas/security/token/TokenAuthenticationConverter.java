package com.mantas.security.token;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.AuthenticationConverter;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class TokenAuthenticationConverter implements AuthenticationConverter {

    private final static String DEFAULT_HEADER_KEY = "Token";
    private final static String DEFAULT_PARAM_KEY = "token";

    private String headerKey = DEFAULT_HEADER_KEY;
    private String paramKey = DEFAULT_PARAM_KEY;

    private AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource;

    public TokenAuthenticationConverter(AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource) {
        this.authenticationDetailsSource = authenticationDetailsSource;
    }

    public TokenAuthenticationConverter(String headerKey, String paramKey, AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource) {
        this.headerKey = headerKey;
        this.paramKey = paramKey;
        this.authenticationDetailsSource = authenticationDetailsSource;
    }

    @Override
    public TokenAuthenticationToken convert(HttpServletRequest request) {
        String token = obtainToken(request);
        if (token == null) {
            return null;
        }
        token = token.trim();

        TokenAuthenticationToken authentication = TokenAuthenticationToken.unauthenticated(token);
        authentication.setDetails(this.authenticationDetailsSource.buildDetails(request));
        return authentication;
    }



    private String obtainToken(HttpServletRequest request) {
        String token = request.getHeader(headerKey);
        if (Objects.isNull(token)) {
            token = request.getParameter(paramKey);
        }
        return token;
    }
}
