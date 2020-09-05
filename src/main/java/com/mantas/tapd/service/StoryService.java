package com.mantas.tapd.service;

import com.mantas.tapd.dto.Story;

import java.util.List;

public interface StoryService {

    List<Story> listByIteration(String iterationId);

    List<Story> listByRelease(String releaseId);

}
