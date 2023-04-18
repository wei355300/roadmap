package com.mantas.tapd;

public interface TapdURL {

    interface URL {
        String STORIES = "https://api.tapd.cn/stories";
        String ROLES = "https://api.tapd.cn/roles";
        String USERS = "https://api.tapd.cn/workspaces/users";
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
        String START = "start";
        String DUE = "due";


        interface STORY {
            String BEGIN = "begin"; //预计开始时间
            String DUE = "due"; //预计结束时间
            String STATUS = "status";
        }
    }

    interface ITERATION {

        interface URL {
            String LIST = "https://api.tapd.cn/iterations";
            String UPDATE = LIST;
        }
        interface PARAM {
            String ID = "id";
            String CURRENT_USER = "current_user";
            String STATUS = "status";
        }

        interface VALUE {
            String STATUS_CLOSE = "done";
            String STATUS_OPEN = "open";
        }
    }


}
