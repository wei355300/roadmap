package com.mantas.tapd.service;

import com.mantas.tapd.dto.Bug;
import com.mantas.tapd.dto.Iteration;

import java.util.Collection;
import java.util.List;

public interface BugService {

    List<List<Bug>> getByIterations(Integer projectId, Collection<Iteration> iterations);

    List<Bug> getByIteration(Integer projectId, String iterationId);
}
