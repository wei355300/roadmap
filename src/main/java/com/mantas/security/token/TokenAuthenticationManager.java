package com.mantas.security.token;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.util.Assert;

public class TokenAuthenticationManager implements AuthenticationManager, InitializingBean  {

    private AuthenticationProvider authenticationProvider;
    private AuthenticationEventPublisher eventPublisher;

    public TokenAuthenticationManager(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Override
    public void afterPropertiesSet() {
        Assert.isTrue(this.authenticationProvider == null, "provider can not be null");
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        if (!authenticationProvider.supports(authentication.getClass())) {
            throw new ProviderNotFoundException("No AuthenticationProvider found for " + authentication.getName());
        }
        Authentication result = authenticationProvider.authenticate(authentication);
        if (result instanceof CredentialsContainer) {
            ((CredentialsContainer) result).eraseCredentials();
        }
        return result;
    }

    private void prepareException(AuthenticationException ex, Authentication auth) {
        if(eventPublisher != null ) {
            this.eventPublisher.publishAuthenticationFailure(ex, auth);
        }
    }
}
