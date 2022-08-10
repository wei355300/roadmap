package com.mantas.security.authority;

import com.mantas.security.account.dto.Account;
import com.mantas.security.authority.dto.Authority;
import org.springframework.util.AntPathMatcher;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class AuthorityGrantor {

    private static AntPathMatcher antMatcher;

    static {
        antMatcher = new AntPathMatcher();
        antMatcher.setTrimTokens(false);
        antMatcher.setCaseSensitive(true);
    }

    private static final Authority grantedAllAuthority = new Authority("/api/**", "ALL");

    /**
     * 分配最小的权限
     * @param account
     */
    public static void grantMinimum(Account account) {
        //fixme 分配最小的权限!!!!
        List<Authority> authorities = account.getAuthorities();
        if (Objects.isNull(authorities)) {
            authorities = Arrays.asList(grantedAllAuthority);
        }
        account.setAuthorities(authorities);
    }

    /**
     * 分配所有的权限
     * @param account
     */
    public static void grantAll(Account account) {
        List<Authority> authorities = account.getAuthorities();
        if (Objects.isNull(authorities)) {
            authorities = Arrays.asList(grantedAllAuthority);
        }
        account.setAuthorities(authorities);
    }

    public static boolean hasAuthority(Account account, Authority authority) {
        List<Authority> grantedAuthorities = account.getAuthorities();
        return grantedAuthorities.stream().anyMatch(grantedAuthority -> {
            boolean isMatchUrl = antMatcher.match(grantedAuthority.getAuthority(), authority.getAuthority());
            if (isMatchUrl) {
                if("ALL".equals(authority.getAction())) {
                    return Boolean.TRUE;
                }
                if(Objects.equals(grantedAuthority.getAction(), authority.getAction())) {
                    return Boolean.TRUE;
                }
            }
            return Boolean.FALSE;
        });
    }
}
