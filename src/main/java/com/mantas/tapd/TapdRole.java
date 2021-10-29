package com.mantas.tapd;

import lombok.Data;

import java.io.Serializable;

@Data
public class TapdRole implements Serializable {

    private static final long serialVersionUID = -3142750238372394496L;

    private String id;
    private String name;

}
