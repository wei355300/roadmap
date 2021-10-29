package com.mantas.tapd.ext.conf;

public interface TapdURL {

    interface URL {
        String STORIES = "https://api.tapd.cn/stories";
        String ROLES = "https://api.tapd.cn/roles";
        String RELEASES = "https://api.tapd.cn/releases";
        String ITERATIONS = "https://api.tapd.cn/iterations";
    }

    interface PARAM {
        String WORKSPACE_ID = "workspace_id";
        String ITERATION_ID = "iteration_id";
        String RELEASE_ID = "release_id";

        String START_DATE = "startdate";
        String END_DATE = "enddate";
        String STATUS = "status";
    }




}
