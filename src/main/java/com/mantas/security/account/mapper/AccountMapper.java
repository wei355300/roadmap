package com.mantas.security.account.mapper;

import com.mantas.security.account.dto.Account;
import com.mantas.security.account.dto.HttpUrlAuthority;
import com.mantas.security.authority.dto.Authority;
import com.mantas.user.dto.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Mapper
public interface AccountMapper {

    @Select("select * from account")
    List<Account> listAccount();

    @Select("select * from account where token=#{token}}")
    Account getAccountByToken(@Param("token") String token);

    @Select("select * from account where user_id=#{userId}}")
    Account getAccountByUserId(@Param("userId") Integer userId);

    @Select("select * from account_authority where account_id=#{accountId}} and scope=#{scope}")
    List<Authority> getAuthoritiesByAccountId(@Param("accountId") Integer accountId, @Param("scope") String scope);

    @Insert("<script>" +
            "  insert into account_authority (account_id, authority, action, scope)" +
            "  values" +
            "  <foreach item='item' collection='authorities' separator=','>" +
            "    (#{accountId}, #{item.authority}, #{item.action}, #{item.scope})" +
            "  </foreach>" +
            "</script>")
    void addAuthorities(@Param("accountId") Integer accountId, @Param("authorities") List<Authority> authorities);


    @Insert("insert into account (user_id, name, token, expiration, non_locked, status) values(#{batchId}, #{productId}, #{number}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Integer addAccount(UserInfo userInfo);
}
