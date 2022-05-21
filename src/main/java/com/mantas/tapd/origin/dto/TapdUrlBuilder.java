package com.mantas.tapd.origin.dto;

public class TapdUrlBuilder {

    private static final String story_pattern = "https://www.tapd.cn/%s/prong/stories/view/%s";
    private static final String task_pattern = "https://www.tapd.cn/%s/prong/tasks/view/%s";
    private static final String bug_pattern = "https://www.tapd.cn/%s/bugtrace/bugs/view?bug_id=%s";

    public static String buildStoryUrl(String projectId, String storyId) {
        return String.format(story_pattern, projectId, storyId);
    }

    public static String buildTaskUrl(String projectId, String taskId) {
        return String.format(task_pattern, projectId, taskId);
    }

    public static String buildBugUrl(String projectId, String bugId) {
        return String.format(bug_pattern, projectId, bugId);
    }
}
