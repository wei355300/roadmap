package com.mantas.tapd.dto.mapper;

import com.mantas.tapd.dto.Release;
import com.mantas.tapd.dto.tapd.TapdReleaseItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TapdReleaseMapper extends StructMapper<TapdReleaseItem, Release> {

    TapdReleaseMapper mapper = Mappers.getMapper(TapdReleaseMapper.class);

    @Mapping(source = "startdate", target = "startDate")
    @Mapping(source = "enddate", target = "endDate")
    @Mapping(source = "workspace_id", target = "workspaceId")
    @Override
    Release mapper(TapdReleaseItem tapdRelease);
}
