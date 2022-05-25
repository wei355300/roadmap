package com.mantas.tapd.service;

import com.mantas.tapd.dto.Iteration;
import com.mantas.tapd.dto.Story;

import java.util.Collection;
import java.util.List;

public interface StoryService {

    List<List<Story>> getByIterations(Integer id, Collection<Iteration> iterations);

    List<Story> getByIteration(Integer projectId, String iterationId);
}
