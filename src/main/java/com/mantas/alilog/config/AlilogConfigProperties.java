package com.mantas.alilog.config;

import lombok.Data;

import java.util.List;

@Data
public class AlilogConfigProperties {

    private List<AlilogItemConfigProperties> entities;
}
