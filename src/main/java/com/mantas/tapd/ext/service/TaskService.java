package com.mantas.tapd.ext.service;

import com.mantas.tapd.ext.dto.Iteration;
import com.mantas.tapd.ext.dto.Task;

import java.util.Collection;
import java.util.List;

public interface TaskService {

    List<List<Task>> getByIterations(Integer projectId, Collection<Iteration> iterations);

    List<Task> getByIterations(Integer projectId, Iteration iteration);
}
