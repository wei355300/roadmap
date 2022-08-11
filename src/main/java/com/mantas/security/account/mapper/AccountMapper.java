package com.mantas.security.account.mapper;

import com.mantas.security.account.dto.Account;
import com.mantas.security.authority.dto.Authority;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AccountMapper {

    @Results(id = "accountResult", value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "nonLocked", column = "non_locked")
    })
    @Select("select * from account where user_id=#{userId}")
    Account getAccountByUserId(@Param("userId") Integer userId);

    @ResultMap("accountResult")
    @Select("select * from account where token=#{token}")
    Account getAccountByToken(@Param("token") String token);

    @ResultMap("accountResult")
    @Select("select * from account")
    List<Account> listAccount();


    @Select("select * from account_authority where account_id=#{accountId}")
    List<Authority> getAuthoritiesByAccountId(@Param("accountId") Integer accountId);

    @Insert("<script>" +
            "  insert into account_authority (account_id, authority, action, scope)" +
            "  values" +
            "  <foreach item='item' collection='authorities' separator=','>" +
            "    (#{accountId}, #{item.authority}, #{item.action}, #{item.scope})" +
            "  </foreach>" +
            "</script>")
    void addAuthorities(@Param("accountId") Integer accountId, @Param("authorities") List<Authority> authorities);


    @Insert("insert into account (user_id, token, expiration, non_locked, status) values(#{userId}, #{token}, #{expiration}, #{nonLocked}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Integer addAccount(Account account);
}
