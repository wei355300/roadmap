package com.mantas.tapd.ext.dto.mapper;

import com.mantas.tapd.TapdStoryItem;
import com.mantas.tapd.ext.dto.Story;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {StoryFiledTranslator.class})
public interface StoryConvert {

    StoryConvert INSTANCE = Mappers.getMapper(StoryConvert.class);

    @Mappings({
            @Mapping(source = "business_value", target = "businessValue"),
            @Mapping(source = "workspace_id", target = "projectId"),
            @Mapping(source = "release_id", target = "releaseId"),
            @Mapping(source = "iteration_id", target = "iterationId"),
            @Mapping(source = "owner", target = "owner", qualifiedByName = {"StoryFiledTranslator", "toOwner"}),
            @Mapping(source = "developer", target = "developer", qualifiedByName = {"StoryFiledTranslator", "toDeveloper"})
    })
    Story toStory(TapdStoryItem story);
}

@Named("StoryFiledTranslator")
class StoryFiledTranslator {

    @Named("toDeveloper")
    public String[] mapDeveloper(String developer) {
        return MapStructConverter.split(developer);
    }

    @Named("toOwner")
    public String[] toOwner(String owner) {
        return MapStructConverter.split(owner);
    }
}