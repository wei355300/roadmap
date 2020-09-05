package com.mantas.tapd.dto.mapper;

public interface StructMapper<K, T> {

    T mapper(K k);
}
