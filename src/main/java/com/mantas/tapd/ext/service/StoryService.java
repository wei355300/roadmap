package com.mantas.tapd.ext.service;

import com.mantas.tapd.ext.dto.Iteration;
import com.mantas.tapd.ext.dto.Story;

import java.util.Collection;
import java.util.List;

public interface StoryService {

    List<List<Story>> getByIterations(Integer id, Collection<Iteration> iterations);

    List<Story> getByIteration(Integer projectId, String iterationId);
}
