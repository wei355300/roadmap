package com.mantas.nacos;

public interface NacosConfigurationParser<T> {

   T parse(String config) throws Exception;
}
