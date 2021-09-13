package com.mantas.connector;

import lombok.Getter;
import org.springframework.util.Assert;

import java.io.Serializable;

@Getter
public class ParamPair implements Serializable {

    private String name;
    private String value;

    public ParamPair(final String name, final String value) {
        Assert.notNull(name, "");
        this.name = name;
        this.value = value;
    }
}
