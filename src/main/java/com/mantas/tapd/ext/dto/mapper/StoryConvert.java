package com.mantas.tapd.ext.dto.mapper;

import com.mantas.tapd.TapdStoryItem;
import com.mantas.tapd.TapdUserItem;
import com.mantas.tapd.ext.dto.Story;
import com.mantas.tapd.ext.dto.Worker;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Objects;

@Mapper
public interface StoryConvert {

    StoryConvert INSTANCE = Mappers.getMapper(StoryConvert.class);

    @Mappings({
            @Mapping(source = "business_value", target = "businessValue"),
            @Mapping(source = "release_id", target = "releaseId"),
            @Mapping(source = "iteration_id", target = "iterationId"),
            @Mapping(source = "developer", target = "developer", qualifiedByName = "storyDeveloperToArray")
    })
    Story toStory(TapdStoryItem story);

    @Named("storyDeveloperToArray")
    static String[] mapDeveloper(TapdStoryItem story) {
        String developer = story.getDeveloper();
        if (Objects.isNull(developer)) {
            return null;
        }
        if (developer.endsWith(";")) {
            developer = developer.substring(developer.length() - 1);
        }
        return developer.split(";");
    }
}
