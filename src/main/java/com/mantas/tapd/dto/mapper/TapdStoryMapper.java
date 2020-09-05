package com.mantas.tapd.dto.mapper;

import com.mantas.tapd.dto.Story;
import com.mantas.tapd.dto.tapd.TapdStoryItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TapdStoryMapper extends StructMapper<TapdStoryItem, Story> {

    TapdStoryMapper mapper = Mappers.getMapper(TapdStoryMapper.class);

    @Mapping(source = "iteration_id", target = "iterationId")
    @Mapping(source = "workspace_id", target = "workspaceId")
    @Override
    Story mapper(TapdStoryItem tapdStory);
}
