package com.mantas.tapd.ext.service;

import com.mantas.tapd.ext.dto.Bug;
import com.mantas.tapd.ext.dto.Iteration;

import java.util.Collection;
import java.util.List;

public interface BugService {

    List<List<Bug>> getByIterations(Integer projectId, Collection<Iteration> iterations);

    List<Bug> getByIteration(Integer projectId, String iterationId);
}
