package com.mantas.tapd.ext.dto.mapper;

import com.mantas.tapd.TapdUrlBuilder;
import com.mantas.tapd.ext.dto.Story;
import com.mantas.tapd.ext.dto.Task;
import com.mantas.tapd.ext.dto.Trace;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {TraceFiledTranslator.class})
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
            @Mapping(constant = "story", target = "type"),
            @Mapping(target = "link", source = "story", qualifiedByName = {"TraceFiledTranslator", "toStoryLink"}),
    })
    Trace toTrace(Story story);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(constant = "task", target = "type"),
            @Mapping(target = "link", source = "task", qualifiedByName = {"TraceFiledTranslator", "toTaskLink"}),
    })
    Trace toTrace(Task task);
}

@Named("TraceFiledTranslator")
class TraceFiledTranslator {

    @Named("toStoryLink")
    public String mapLink(Story story) {
        return TapdUrlBuilder.buildStoryUrl(String.valueOf(story.getProjectId()), story.getId());
    }

    @Named("toTaskLink")
    public String mapLink(Task task) {
        return TapdUrlBuilder.buildTaskUrl(String.valueOf(task.getProjectId()), task.getId());
    }
}
