package com.mantas.tapd.ext.dto.mapper;

import java.util.Objects;

public class MapStructConverter {

    /**
     * 按分号(;)分隔字串
     * @param str
     * @return
     */
    public static String[] split(String str) {
        if (Objects.isNull(str)) {
            return null;
        }
        if (str.endsWith(";")) {
            str = str.substring(0, str.length() - 1);
        }
        return str.split(";");
    }
}
