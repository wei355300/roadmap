package com.mantas.tapd.ext.dao;

import com.mantas.tapd.ext.dto.Story;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class StoryDao<T> {

    private DeveloperStoryDs developerStoryDs;

    public StoryDao() {
        developerStoryDs = new DeveloperStoryDs();
    }

    public List<Story> sort(String developer, List<Story> stories) {
        List<Story> sorted = developerStoryDs.getSort(developer);
        if (Objects.isNull(sorted)) {
            return stories;
        }
        return sort(sorted, stories);
    }

    private List<Story> sort(List<Story> sorted, List<Story> sorting) {
        return sorting;//todo
    }
}

class DeveloperStoryDs {

    private Map<String, List<Story>> devStories = new HashMap<>();

    protected List<Story> getSort(String developer) {
        return devStories.get(developer);
    }
}
