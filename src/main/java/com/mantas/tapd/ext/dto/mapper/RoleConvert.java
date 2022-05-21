package com.mantas.tapd.ext.dto.mapper;

import com.mantas.tapd.origin.dto.TapdUserItem;
import com.mantas.tapd.ext.dto.Worker;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleConvert {

    RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);

    @Mappings({
            @Mapping(source = "user", target = "user"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "email", target = "email"),
//            @Mapping(source = "role_id", target = "roles")
    })
    Worker toWorker(TapdUserItem user);
}
