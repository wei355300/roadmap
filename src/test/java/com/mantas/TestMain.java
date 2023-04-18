package com.mantas;

import org.apache.commons.lang3.StringUtils;

public class TestMain {
    public static void main(String[] args) {
        String[] s = new String[]{"1", "2", "3"};
        System.out.println(StringUtils.join(s, "|"));
    }
}
