package com.mantas.tapd.connector;

import com.mantas.tapd.conf.TapdConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlBuiler {

    // https://www.tapd.cn/{workspaceId}/prong/stories/view/{storyId}
    private static final String story_pattern = "https://www.tapd.cn/%s/prong/stories/view/%s";

    private Optional<TapdConf> tapdConf;

    private static UrlBuiler ins;

    public UrlBuiler(@Autowired TapdConf tapdConf) {
        this.tapdConf = Optional.of(tapdConf);
        ins = this;
    }

    public static String buildViewStoryUrl(String storyId) {

        return ins.tapdConf.isPresent() ? (String.format(story_pattern, ins.tapdConf.get().getDefaultWorkspaceId(), storyId)) : "";
    }
}
