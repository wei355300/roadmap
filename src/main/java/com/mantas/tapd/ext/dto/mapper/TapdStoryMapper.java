package com.mantas.tapd.ext.dto.mapper;

import com.mantas.tapd.ext.dto.Story;
import com.mantas.tapd.TapdStoryItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TapdStoryMapper extends StructMapper<TapdStoryItem, Story> {

    TapdStoryMapper mapper = Mappers.getMapper(TapdStoryMapper.class);

//    @Mapping(source = "iteration_id", target = "iterationId")
//    @Mapping(source = "workspace_id", target = "workspaceId")
    @Override
    Story mapper(TapdStoryItem tapdStory);
}
