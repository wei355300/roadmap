package com.mantas.tapd.ext.service;

import com.mantas.tapd.ext.dto.Iteration;

import java.util.Date;
import java.util.List;

public interface IterationService {

    List<Iteration> list(Date startDate);

    List<Iteration> getIterationsByProject(Integer projectId);
}
