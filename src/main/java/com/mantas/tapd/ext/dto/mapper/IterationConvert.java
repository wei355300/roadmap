package com.mantas.tapd.ext.dto.mapper;

import com.mantas.tapd.TapdIteration;
import com.mantas.tapd.TapdIterationItem;
import com.mantas.tapd.ext.dto.Iteration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IterationConvert {

    IterationConvert INSTANCE = Mappers.getMapper(IterationConvert.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "startdate", target = "startDate"),
            @Mapping(source = "enddate", target = "endDate")
    })
    Iteration toIteration(TapdIterationItem iteration);

    @Mappings({
            @Mapping(source = "iteration.id", target = "id"),
            @Mapping(source = "iteration.name", target = "name"),
            @Mapping(source = "iteration.status", target = "status"),
            @Mapping(source = "iteration.startdate", target = "startDate"),
            @Mapping(source = "iteration.enddate", target = "endDate")
    })
    Iteration toIteration(TapdIteration iteration);

    List<Iteration> toIterations(List<TapdIteration> iteration);
}
