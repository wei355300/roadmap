package com.mantas.security.account.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mantas.security.account.dto.Account;
import com.mantas.security.account.dto.AccountMapStruct;
import com.mantas.security.account.mapper.AccountMapper;
import com.mantas.security.authority.AuthorityGrantor;
import com.mantas.security.authority.dto.Authority;
import com.mantas.security.token.TokenGenerator;
import com.mantas.user.dto.UserInfo;
import com.mantas.user.service.UserService;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class AccountService {
    private AccountMapper accountMapper;
    private UserService userService;

    public AccountService(UserService userService, AccountMapper accountMapper) {
        this.userService = userService;
        this.accountMapper = accountMapper;
    }

    @Transactional
    public Account createAccount(UserInfo user) {
        // 创建用户信息
        user = userService.addOrUpdateUser(user);
        // 创建账号
        Account account = addAccount(user);
        //分配权限
        grantAuthorities(account);
        return account;
    }

    @Transactional
    public Account updateToken(Account account) {
        String newToken = TokenGenerator.randomToken();
        LocalDateTime expiration = defaultExpiration();
        account.setToken(newToken);
        account.setExpiration(expiration);
        accountMapper.updateToken(account, newToken, expiration);
        return account;
    }

    public Account getAccountWithAuthoritiesByMobile(String mobile) {
        UserInfo user = userService.getUserByMobile(mobile);
        if (Objects.isNull(user)) {
            return null;
        }
        return getAccountWithAuthoritiesByUserId(user.getId());
    }

    public Account getAccountWithAuthoritiesByUserId(Integer userId) {
        Account account = accountMapper.getAccountByUserId(userId);
        List<Authority> authorities = getAuthoritiesByAccountId(account.getId());
        account.setAuthorities(authorities);
        return account;
    }

    public Account getAccountWithAuthoritiesByToken(@NotNull String token) {
        Account account = accountMapper.getAccountByToken(token);
        if (Objects.isNull(account)) {
            return null;
        }
        List<Authority> authorities = getAuthoritiesByAccountId(account.getId());
        account.setAuthorities(authorities);
        return account;
    }

    public PageInfo<Account> getAccountList(int pageSize, int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<Account> accountList = accountMapper.listAccount();
        return new PageInfo<>(accountList);
    }

    private List<Authority> getAuthoritiesByAccountId(Integer id) {
        List<Authority> authorities = accountMapper.getAuthoritiesByAccountId(id);
        return authorities;
    }

    private Account addAccount(UserInfo userInfo) {
        Account account = defaultAccount(userInfo);
        Integer accountId = accountMapper.addOrUpdateAccount(account);
        account.setId(accountId);
        return account;
    }

    private Account defaultAccount(UserInfo userInfo) {
        Account account = AccountMapStruct.ins.toAccount(userInfo);
        account.setToken(TokenGenerator.randomToken());
        account.setExpiration(defaultExpiration());
        account.setNonLocked(true);
        account.setStatus(Boolean.TRUE);
        return account;
    }

    private LocalDateTime defaultExpiration() {
        return LocalDateTime.now().plusDays(7);
    }

    /**
     * 分配权限, 并保存
     * @param account
     */
    private void grantAuthorities(Account account) {
        AuthorityGrantor.grantMinimum(account);
        accountMapper.addAuthorities(account.getId(), account.getAuthorities());
    }
}
