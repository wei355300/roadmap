package com.mantas.security.account.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mantas.security.account.dto.Account;
import com.mantas.security.account.dto.AccountMapStruct;
import com.mantas.security.account.mapper.AccountMapper;
import com.mantas.security.authority.AuthorityGrantor;
import com.mantas.security.authority.dto.Authority;
import com.mantas.user.dto.UserInfo;
import com.mantas.user.service.UserService;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

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
        user = userService.addUser(user);
        // 创建账号
        Account account = addAccount(user);
        //分配权限
        grantAuthorities(account);
        return account;
    }

    public Account getAccountByMobile(String mobile) {
        UserInfo user = userService.getUserByMobile(mobile);
        return getAccountByUserId(user.getId());
    }

    public Account getAccountByUserId(Integer userId) {
        return accountMapper.getAccountByUserId(userId);
    }

    public Account getAccountByToken(@NotNull String token) {
        Account account = accountMapper.getAccountByToken(token);
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
        List<Authority> authorities = accountMapper.getAuthoritiesByAccountId(id, "http");
        return authorities;
    }

    private Account addAccount(UserInfo userInfo) {
        Account account = defaultAccount(userInfo);
        Integer accountId = accountMapper.addAccount(userInfo);
        account.setId(accountId);
        return account;
    }

    private Account defaultAccount(UserInfo userInfo) {
        Account account = AccountMapStruct.ins.toAccount(userInfo);
        account.setToken(TokenGenerator.randomToken());
        account.setExpiration();
        account.setNonLocked(true);
        account.setStatus(Boolean.TRUE);
        return account;
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
