package com.mantas.alilog.controller.test;

import org.springframework.web.util.UriComponentsBuilder;

public class Main {

    public static void main(String[] args) {
        String entity = "B2B订货平台";
        String logStore = "B2B订货平台";
        String query = "搜索热词";

        String path = UriComponentsBuilder.newInstance().pathSegment("api/alilog/query").pathSegment(entity).pathSegment(logStore).pathSegment(query).encode().build().getPath();
        System.out.println(path);
    }
}
