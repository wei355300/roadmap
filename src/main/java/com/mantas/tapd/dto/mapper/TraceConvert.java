package com.mantas.tapd.dto.mapper;

import com.mantas.tapd.TapdUrlBuilder;
import com.mantas.tapd.bug.Bug;
import com.mantas.tapd.story.Story;
import com.mantas.tapd.task.Task;
import com.mantas.tapd.dto.Trace;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {TraceFiledTranslator.class})
public interface TraceConvert {

    TraceConvert INSTANCE = Mappers.getMapper(TraceConvert.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "businessValue", target = "weight"),
            @Mapping(constant = "story", target = "type"),
            @Mapping(source = "workspaceId", target = "projectId"),
            @Mapping(source = "begin", target = "start"),
            @Mapping(source = "due", target = "end"),
            @Mapping(source = "story", target = "link", qualifiedByName = {"TraceFiledTranslator", "toStoryLink"}),
            @Mapping(source = "story", target = "done", qualifiedByName = {"TraceFiledTranslator", "toStoryStatus"}),
    })
    Trace toTrace(Story story);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(constant = "task", target = "type"),
            @Mapping(target = "link", source = "task", qualifiedByName = {"TraceFiledTranslator", "toTaskLink"}),
            @Mapping(source = "begin", target = "start"),
            @Mapping(source = "due", target = "end"),
    })
    Trace toTrace(Task task);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "title", target = "name"),
            @Mapping(constant = "bug", target = "type"),
            @Mapping(source = "begin", target = "start"),
            @Mapping(source = "due", target = "end"),
            @Mapping(source = "bug", target = "link", qualifiedByName = {"TraceFiledTranslator", "toBugLink"}),
            @Mapping(source = "bug", target = "done", qualifiedByName = {"TraceFiledTranslator", "toBugStatus"}),
    })
    Trace toTrace(Bug bug);
}

@Named("TraceFiledTranslator")
class TraceFiledTranslator {

    @Named("toStoryLink")
    public String mapLink(Story story) {
        return TapdUrlBuilder.buildStoryUrl(String.valueOf(story.getWorkspaceId()), story.getId());
    }

    @Named("toStoryStatus")
    public Boolean mapStatus(Story story) {
        return "resolved".equals(story.getStatus()) || "closed".equals(story.getStatus());
    }

    @Named("toBugStatus")
    public Boolean mapStatus(Bug bug) {
        return "resolved".equals(bug.getStatus()) || "closed".equals(bug.getStatus());
    }

    @Named("toTaskLink")
    public String mapLink(Task task) {
        return TapdUrlBuilder.buildTaskUrl(String.valueOf(task.getProjectId()), task.getId());
    }

    @Named("toBugLink")
    public String mapLink(Bug bug) {
        return TapdUrlBuilder.buildBugUrl(String.valueOf(bug.getProjectId()), bug.getId());
    }
}
