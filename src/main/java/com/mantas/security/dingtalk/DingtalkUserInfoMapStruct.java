package com.mantas.security.dingtalk;

import com.aliyun.dingtalkcontact_1_0.models.GetUserResponseBody;
import com.mantas.user.dto.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DingtalkUserInfoMapStruct {

    DingtalkUserInfoMapStruct ins = Mappers.getMapper(DingtalkUserInfoMapStruct.class);

    @Mappings({
            @Mapping(source = "avatarUrl", target = "avatarUrl"),
            @Mapping(constant = "mobile", target = "mobile"),
            @Mapping(constant = "nick", target = "nick"),
            @Mapping(constant = "nick", target = "name")
    })
    UserInfo toUser(GetUserResponseBody getUserResponseBody);
}
