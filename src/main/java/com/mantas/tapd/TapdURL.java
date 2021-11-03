package com.mantas.tapd;

public interface TapdURL {

    interface URL {
        String STORIES = "https://api.tapd.cn/stories";
        String ROLES = "https://api.tapd.cn/roles";
        String USERS = "https://api.tapd.cn/workspaces/users";
        String ITERATIONS = "https://api.tapd.cn/iterations";
        String TASKS = "https://api.tapd.cn/tasks";
        String BUGS = "https://api.tapd.cn/bugs";
        String RELEASES = "https://api.tapd.cn/releases";
    }

    interface PARAM {
        String WORKSPACE_ID = "workspace_id";
        String ITERATION_ID = "iteration_id";
        String RELEASE_ID = "release_id";

        String START_DATE = "startdate";
        String END_DATE = "enddate";
        String STATUS = "status";
        String FIELDS = "fields";
        String LIMIT = "limit";
    }




}
