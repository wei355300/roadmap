package com.mantas.security.account.mapper;

import com.mantas.security.account.dto.Account;
import com.mantas.security.authority.dto.Authority;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
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

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into account (user_id, name, token, expiration, non_locked, status) values(#{userId}, #{name}, #{token}, #{expiration}, #{nonLocked}, #{status})")
    Integer addAccount(Account account);

    @Insert("update account set name=#{name}, token=#{token}, expiration=#{expiration}, non_locked=#{nonLocked}, status=#{status} where user_id=#{userId}")
    void updateAccount(Account account);

    @Update("update account set token=#{newToken}, expiration=#{expiration} where id=#{account.id}")
    void updateToken(@Param("account") Account account, @Param("newToken") String newToken, @Param("expiration") LocalDateTime expiration);
}
