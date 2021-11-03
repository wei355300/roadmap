package com.mantas.tapd.ext.dto.tapd;

import lombok.Data;

@Data
public class TapdRelese implements TapdDataIt {

    private TapdReleaseItem Release;

    @Override
    public TapdReleaseItem getEntity() {
        return Release;
    }
}
