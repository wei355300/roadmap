package com.mantas.tapd.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlBuilder {

    private static final String story_pattern = "https://www.tapd.cn/%s/prong/stories/view/%s";

    private Optional<TapdConf> tapdConf;

    private static UrlBuilder ins;

    public UrlBuilder(@Autowired TapdConf tapdConf) {
        this.tapdConf = Optional.of(tapdConf);
        ins = this;
    }

    public static String buildViewStoryUrl(String storyId) {
        return ins.tapdConf.isPresent() ? (String.format(story_pattern, ins.tapdConf.get().getDefaultWorkspaceId(), storyId)) : "";
    }
}
