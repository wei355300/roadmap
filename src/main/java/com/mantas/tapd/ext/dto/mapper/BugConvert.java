package com.mantas.tapd.ext.dto.mapper;

import com.mantas.tapd.TapdBugItem;
import com.mantas.tapd.ext.dto.Bug;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {BugFiledTranslator.class})
public interface BugConvert {

    BugConvert INSTANCE = Mappers.getMapper(BugConvert.class);

    @Mappings({
            @Mapping(source = "workspace_id", target = "projectId"),
            @Mapping(source = "iteration_id", target = "iterationId"),
            @Mapping(source = "de", target = "developer", qualifiedByName = {"BugFiledTranslator", "toDeveloper"}),
            @Mapping(source = "te", target = "tester", qualifiedByName = {"BugFiledTranslator", "toTester"})
    })
    Bug toBug(TapdBugItem bug);
}

@Named("BugFiledTranslator")
class BugFiledTranslator {

    @Named("toDeveloper")
    public String[] toDeveloper(String developer) {
        return MapStructConverter.split(developer);
    }

    @Named("toTester")
    public String[] toTester(String tester) {
        return MapStructConverter.split(tester);
    }
}