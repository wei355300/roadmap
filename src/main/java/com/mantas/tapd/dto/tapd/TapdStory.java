package com.mantas.tapd.dto.tapd;

import lombok.Data;

@Data
public class TapdStory implements TapdDataIt {

    private TapdStoryItem Story;

    @Override
    public Object getEntity() {
        return Story;
    }
}
