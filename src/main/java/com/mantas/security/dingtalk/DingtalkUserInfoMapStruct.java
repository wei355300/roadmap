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
            @Mapping(source = "mobile", target = "mobile"),
            @Mapping(source = "nick", target = "nick"),
            @Mapping(source = "nick", target = "name")
    })
    UserInfo toUser(GetUserResponseBody getUserResponseBody);
}
