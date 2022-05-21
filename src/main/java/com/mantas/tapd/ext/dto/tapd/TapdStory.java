package com.mantas.tapd.ext.dto.tapd;

import com.mantas.tapd.origin.dto.TapdStoryItem;
import lombok.Data;

@Data
public class TapdStory implements TapdDataIt {

    private TapdStoryItem Story;

    @Override
    public Object getEntity() {
        return Story;
    }
}
