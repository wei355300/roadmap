package com.mantas.tapd.ext.dto.mapper;

import com.mantas.tapd.TapdTaskItem;
import com.mantas.tapd.ext.dto.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Objects;

@Mapper(uses = {TaskFiledTranslator.class})
public interface TaskConvert {

    TaskConvert INSTANCE = Mappers.getMapper(TaskConvert.class);

    @Mappings({
            @Mapping(source = "workspace_id", target = "projectId"),
            @Mapping(source = "iteration_id", target = "iterationId"),
            @Mapping(source = "owner", target = "owner", qualifiedByName = {"TaskFiledTranslator", "toOwner"}),
    })
    Task toTask(TapdTaskItem task);
}

@Named("TaskFiledTranslator")
class TaskFiledTranslator {

    @Named("toOwner")
    public String[] toOwner(String owner) {
        if (Objects.isNull(owner)) {
            return null;
        }
        if (owner.endsWith(";")) {
            owner = owner.substring(0, owner.length() - 1);
        }
        return owner.split(";");
    }
}