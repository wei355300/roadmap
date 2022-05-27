package com.mantas.alilog.controller.req;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mantas.utils.JsonUtils;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

public class QueryParamsEditor extends PropertyEditorSupport {
    /**
     * Sets the property value by parsing a given String.  May raise
     * java.lang.IllegalArgumentException if either the String is
     * badly formatted or if this kind of property can't be expressed
     * as text.
     *
     * @param text The string to be parsed.
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (!StringUtils.hasLength(text)) {
            setValue(null);
        } else {
            QueryParams params;
            try {
                params = JsonUtils.toObj(text, QueryParams.class);
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException(e);
            }
            setValue(params);
        }
    }
}
