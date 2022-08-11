package com.mantas.security.account.dto;

import com.mantas.user.dto.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapStruct {

    AccountMapStruct ins = Mappers.getMapper(AccountMapStruct.class);

    @Mappings({
            @Mapping(source = "id", target = "userId")
    })
    Account toAccount(UserInfo userInfo);
}
