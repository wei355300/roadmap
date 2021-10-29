package com.mantas.tapd.ext.service;

import com.mantas.tapd.ext.dto.Story;

import java.util.List;

public interface StoryService {

    List<Story> listByIteration(String iterationId);

    List<Story> listByRelease(String releaseId);

}
