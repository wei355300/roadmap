package com.mantas.security.dingtalk;

import com.mantas.security.account.AccountChecker;
import com.mantas.security.account.dto.Account;
import com.mantas.security.token.TokenAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.Assert;

@Slf4j
public class DingtalkAuthenticationProvider implements AuthenticationProvider {

    protected final Log logger = LogFactory.getLog(getClass());

    private AccountChecker preAuthenticationChecks = new AccountChecker();

    private DingtalkDetailServices dingtalkDetailServices;

    public DingtalkAuthenticationProvider(DingtalkDetailServices dingtalkDetailServices) {
        this.dingtalkDetailServices = dingtalkDetailServices;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(DingtalkAuthenticationToken.class, authentication, () -> "Only DingtalkAuthenticationToken is supported");
        DingtalkAuthenticationToken dingtalkAuthenticationToken = (DingtalkAuthenticationToken) authentication;
        String authCode = determineAuthCode(dingtalkAuthenticationToken);
        Account account;
        try {
            account = retrieveUser(authCode, dingtalkAuthenticationToken);
            if (account == null) {
                this.logger.debug("Failed to find account by dingtalk authCode: '" + authCode + "'");
                throw new BadCredentialsException("Bad credentials");
            }
            this.preAuthenticationChecks.check(account);
            additionalAuthenticationChecks(account, dingtalkAuthenticationToken);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception e) {
            log.warn("find account error", e);
            throw new BadCredentialsException("Bad credentials");
        }
//        this.postAuthenticationChecks.check(user);
        return createSuccessAuthentication(authCode, dingtalkAuthenticationToken, account);
    }

    private String determineAuthCode(DingtalkAuthenticationToken dingtalkAuthenticationToken) {
        return dingtalkAuthenticationToken.getAuthCode();
    }

    private Account retrieveUser(String authCode, DingtalkAuthenticationToken authentication) throws AuthenticationException {
        try {
            return dingtalkDetailServices.getAccountByAuthCode(authCode);
        } catch (Exception e) {
            log.warn("can not find account", e);
            throw new BadCredentialsException("Bad credentials");
        }
    }


    private void additionalAuthenticationChecks(Account account, DingtalkAuthenticationToken authentication) throws AuthenticationException {
//        if (authentication.getCredentials() == null) {
//            this.logger.debug("Failed to authenticate since no credentials provided");
//            throw new BadCredentialsException("Bad credentials");
//        }
    }

    private Authentication createSuccessAuthentication(String token, Authentication authentication, Account account) {
        TokenAuthenticationToken result = TokenAuthenticationToken.authenticated(token, account);
        result.setDetails(authentication.getDetails());
        log.debug("Authenticated user");
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (DingtalkAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
