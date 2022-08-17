package com.mantas.security.token;

import com.mantas.security.account.AccountChecker;
import com.mantas.security.account.dto.Account;
import com.mantas.security.account.exception.AccountNotExistException;
import com.mantas.security.authority.AuthorityUrlCheckerAuthorizationManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.Assert;

/**
 * 自定义Token的鉴权.
 *  <br />
 * 获得从 {@link TokenAuthenticationFilter} 传递的 TokenAuthenticationToken 实例后, <br />
 * 通过token获取账号(account)信息, 并将account设置到 TokenAuthenticationToken 实例中, <br />
 * 为后续的授权流程使用, 参考: {@link AuthorityUrlCheckerAuthorizationManager}
 */
@Slf4j
public class TokenAuthenticationProvider implements AuthenticationProvider {

    private AccountChecker preAuthenticationChecks = new AccountChecker();

    private TokenDetailServices tokenDetailServices;

    public TokenAuthenticationProvider(TokenDetailServices tokenDetailServices) {
        this.tokenDetailServices = tokenDetailServices;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(TokenAuthenticationToken.class, authentication, () -> "Only TokenAuthenticationToken is supported");
        TokenAuthenticationToken tokenAuthenticationToken = (TokenAuthenticationToken) authentication;
        String token = tokenAuthenticationToken.getToken();
        Account account;
        try {
            account = retrieveAccount(token, tokenAuthenticationToken);
            if (account == null) {
                log.debug("Failed to find account by token: '" + token + "'");
                throw new BadCredentialsException("Bad credentials");
            }
            this.preAuthenticationChecks.check(account);
            additionalAuthenticationChecks(account, tokenAuthenticationToken);
        } catch (AuthenticationException ex) {
            throw ex;
        }
        catch (Exception e) {
            log.warn("find account error", e);
            throw new BadCredentialsException("Bad credentials");
        }
//        this.postAuthenticationChecks.check(user);
        return createSuccessAuthentication(token, authentication, account);
    }

    private Account retrieveAccount(String token, TokenAuthenticationToken authentication) throws AuthenticationException, AccountNotExistException {
        return tokenDetailServices.getAccountByToken(token);
    }


    private void additionalAuthenticationChecks(Account account, TokenAuthenticationToken authentication) throws AuthenticationException {
//        if (authentication.getCredentials() == null) {
//            log.debug("Failed to authenticate since no credentials provided");
//            throw new BadCredentialsException("Bad credentials");
//        }

        //如果账号的token过期时间太短, 需要延迟一定的时间
    }
    private Authentication createSuccessAuthentication(String token, Authentication authentication, Account account) {
        TokenAuthenticationToken result = TokenAuthenticationToken.authenticated(token, account);
        result.setDetails(authentication.getDetails());
        log.debug("Authenticated user");
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (TokenAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
