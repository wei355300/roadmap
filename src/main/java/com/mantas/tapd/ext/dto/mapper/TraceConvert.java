package com.mantas.tapd.ext.dto.mapper;

import com.mantas.tapd.ext.dto.Story;
import com.mantas.tapd.ext.dto.Trace;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TraceConvert {

    TraceConvert INSTANCE = Mappers.getMapper(TraceConvert.class);

    //fixme
    // 设置 type 常量
    // 设置 link 链接
    // 设置 done 常量
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "businessValue", target = "weight"),
    })
    Trace toTrace(Story story);
}
