package com.mantas.tapd.ext.dto.mapper;

public interface StructMapper<K, T> {

    T mapper(K k);
}
